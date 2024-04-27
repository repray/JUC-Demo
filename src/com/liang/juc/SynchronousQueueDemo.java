package com.liang.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他的BlockingQueue不一样,SynchronousQueue只能存储一个元素,要存储新的元素,必须将队列中已有的元素取出
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " put "+i);
                    blockingQueue.put(String.valueOf(i));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+" take "+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+" take "+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+" take "+blockingQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t2").start();
    }
}
