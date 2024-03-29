package com.liang.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock的用法示例
 */
public class saleTicketDemoByLock {
    public static void main(String[] args) {
        TicketByLock ticket = new TicketByLock();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

class TicketByLock {
    private Integer num = 10;

    Lock lock = new ReentrantLock();


    public void sale() {
        //加锁
        lock.lock();
        try {
            //编写业务代码
            if (num >= 1) {
                num--;
                System.out.println(Thread.currentThread().getName() + "已卖出第" + (10 - num) + "张票,还剩余" + num + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }

    }
}
