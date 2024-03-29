package com.liang.juc;

/**
 * 线程之间的通信问题
 * 生产者和消费者问题 等待唤醒,通知唤醒
 * 线程交替执行 A B 同时操作一个变量 num=0
 * A num+1
 * B num-1
 */
public class ProducerConsumerDemo {
    public static void main(String[] args) {

        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "D").start();

    }
}

class Data {
    private Integer num = 0;

    public synchronized void increment() throws InterruptedException {
        while (num != 0) {
            //判断是否需要等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "->" + num);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            //判断是否需要等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "->" + num);
        this.notifyAll();
    }
}


