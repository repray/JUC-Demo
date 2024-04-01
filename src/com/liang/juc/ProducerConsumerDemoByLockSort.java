package com.liang.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间的通信问题
 * 生产者和消费者问题 等待唤醒,通知唤醒
* 线程交替执行 A B 同时操作一个变量 num=0
 * A num+1
 * B num-1
 * 使用Lock和 Condition.await Condition.signalAll 代替synchronized wait notifyAll
 * 增加排序,使线程顺序执行
 * A执行完调用B,B执行完调用C,C执行完调用A
 */
public class ProducerConsumerDemoByLockSort {
    public static void main(String[] args) {
        DataByLockSort data = new DataByLockSort();
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();
    }
}

class DataByLockSort {

    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (num != 1) {
                conditionA.await();
            }
            num = 2;
            System.out.println("当前是线程" + Thread.currentThread().getName() + "AAAAAAA");
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (num != 2) {

            }
            num = 3;
            System.out.println("当前是线程" + Thread.currentThread().getName() + "BBBBBBB");
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (num != 3) {
                conditionC.await();
            }
            num = 1;
            System.out.println("当前是线程" + Thread.currentThread().getName() + "CCCCCCC");
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


