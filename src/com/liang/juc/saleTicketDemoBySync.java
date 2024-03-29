package com.liang.juc;

/**
 * synchronized的用法示例
 */
public class saleTicketDemoBySync {
    public static void main(String[] args) {
        TicketBySync ticket = new TicketBySync();

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

class TicketBySync {
    private Integer num = 10;

    public synchronized void sale() {
        if (num >= 1) {
            num--;
            System.out.println(Thread.currentThread().getName() + "已卖出第" + (10 - num) + "张票,还剩余" + num + "张票");
        }
    }
}
