package com.zp.lims.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.concurrent.CompletableFuture;

/**
 * Author : zhengpanone
 * Date : 2025/3/25 13:21
 * Version : v1.0.0
 * Description: 线程上下文工具类
 */
@Slf4j
public class ThreadContextUtil {

    // 设置线程上下文，任务类型和线程前缀
    public static void setThreadContext(String threadPrefix, String taskType) {
        // 设置线程名称前缀
        Thread.currentThread().setName(threadPrefix + "-" + Thread.currentThread().getId());
        // 设置任务类型
        MDC.put("taskType", taskType);
    }

    // 设置线程上下文，任务类型和线程前缀
    public static void setThreadContext(String threadPrefix, String taskType, String actionCode) {
        // 设置线程名称前缀
        setThreadContext(threadPrefix, taskType);
        MDC.put("actionCode", actionCode);
    }

    // 清除线程上下文
    public static void clearThreadContext() {
        // 清理线程名称
        Thread.currentThread().setName("Thread-" + Thread.currentThread().getId());
        // 清理MDC上下文
        MDC.clear();
    }

    // 封装 CompletableFuture 自动清理 MDC
    public static <T> CompletableFuture<T> wrapAsync(CompletableFuture<T> future) {
        return future.whenComplete((result, ex) -> ThreadContextUtil.clearThreadContext());
    }
}
