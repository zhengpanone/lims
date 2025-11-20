package com.zp.lims.thread.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author : zhengpanone
 * Date : 2024/9/29 11:03
 * Version : v1.0.0
 * Description: TODO
 */
@Slf4j
public class AsyncThread extends Thread {
    @Override
    public void run() {
        System.out.println("Current thread name:" + Thread.currentThread().getName() + " Send email success!");
    }

    public static void main(String[] args) {
        AsyncThread asyncThread = new AsyncThread();
        asyncThread.run();

        asyncThread.fun();
    }

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void fun() {
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                log.info("执行业务逻辑");
            }
        });
    }
}
