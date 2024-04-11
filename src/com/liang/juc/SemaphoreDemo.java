package com.liang.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * acquire 获得,假设如果已经满了,达到数量上限,则等待,等待被释放位置
 * release 释放,会将当前的信号量-1,然后唤醒等待的线程
 * 作用 多个共享资源互斥的使用! 并发限流,控制最大的线程数
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程数量: 停车位 主要用于限流等场景
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                //获得
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    //释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
