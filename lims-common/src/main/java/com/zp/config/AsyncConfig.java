package com.zp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    // 核心池大小 = CPU核心数 * 倍数
    @Value("${async.executor.core-multiplier:2}")
    private int coreMultiplier;

    // 最大池大小 = CPU核心数 * 倍数
    @Value("${async.executor.max-multiplier:5}")
    private int maxMultiplier;

    @Value("${async.executor.queueCapacity:100}")
    private int queueCapacity;

    @Value("${async.executor.defaultThreadPrefix:Async-Default}")
    private String defaultThreadPrefix;


    @Bean("sharedExecutor")
    public ThreadPoolTaskExecutor sharedExecutor() {
        // 获取CPU核数
        int cpuCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(cpuCores * coreMultiplier);
        executor.setMaxPoolSize(cpuCores * maxMultiplier);
        executor.setQueueCapacity(queueCapacity);

        // 线程工厂：动态设置线程前缀
        executor.setThreadFactory(new CustomThreadFactory(defaultThreadPrefix));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        executor.initialize();
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        // Spring @Async 默认使用这个线程池
        return sharedExecutor();
    }

    static class CustomThreadFactory implements ThreadFactory {
        private final String threadPrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private static final int MAX_THREAD_NUM = 10000; // 线程编号最大值

        public CustomThreadFactory(String threadPrefix) {
            this.threadPrefix = threadPrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            int threadNum = threadNumber.getAndUpdate(n -> (n >= MAX_THREAD_NUM) ? 1 : n + 1); // 自动归零
            Thread thread = new Thread(r);
            thread.setName(threadPrefix + "-" + threadNum);
            return thread;
        }
    }
}