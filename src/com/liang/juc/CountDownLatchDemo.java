package com.liang.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 减法计数器
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // new CountDownLatch(6); 6是总数
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread((() -> {
                System.out.println(Thread.currentThread().getName() + "go out");
                countDownLatch.countDown();
            }), String.valueOf(i)).start();
        }
        // -1
        // countDownLatch.countDown();
        //等待计数器归零,继续向下执行
        countDownLatch.await();

        System.out.println("结束");
    }
}
