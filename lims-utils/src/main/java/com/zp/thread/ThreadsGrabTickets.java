package com.zp.thread;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author : zhengpanone
 * Date : 2024/9/29 10:37
 * Version : v1.0.0
 * Description:
 */
public class ThreadsGrabTickets {
    public static void main(String[] args) {
        TickSystem system = CodeSandboxFactory.newInstance("Synchronized");
        //  TicketSystem system = CodeSandboxFactory.newInstance("ReentrantLock");
        //  TicketSystem system = CodeSandboxFactory.newInstance("Semaphore");
        new Thread(system::sellTicket, "线程1").start();
        new Thread(system::sellTicket, "线程2").start();

    }

    static class CodeSandboxFactory {
        static TickSystem newInstance(String type) {
            switch (type) {
                case "Synchronized":
                    return new TicketSystemBySynchronized();
                case "ReentrantLock":
                    return new TicketSystemReentrantLock();
                case "Semaphore":
                default:
                    return new TicketSystemBySemaphore();
            }
        }
    }

    static class TicketSystemReentrantLock implements TickSystem {
        private int tickets = 100;
        private final ReentrantLock lock = new ReentrantLock();

        public void sellTicket() {
            while (tickets > 0) {
                lock.lock();
                try {
                    Thread.sleep(200);
                    if (tickets > 0) {
                        System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余票数" + --tickets);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TicketSystemBySemaphore implements TickSystem {
        private final Semaphore semaphore;

        public TicketSystemBySemaphore() {
            //总共100张票
            this.semaphore = new Semaphore(100);
        }

        public void sellTicket() {
            //返回此信号量中当前可用的许可证数
            int i = semaphore.availablePermits();
            while (i > 0) {
                try {
                    Thread.sleep(200);
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余票数" + --i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放信号量，允许其他线程获取信号量
                    semaphore.release();
                }
            }
        }
    }

    static class TicketSystemBySynchronized implements TickSystem {
        private int tickets = 100;

        public void sellTicket() {
            while (tickets > 0) {
                synchronized (this) {
                    try {
                        if (tickets > 0) {
                            System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余票数" + --tickets);
                        }
                        // 模拟售票
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
