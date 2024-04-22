package com.liang.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁 线程独自占有
 * 共享锁 线程共享
 *
 * 读-读 锁可以共享
 * 读-写 写-写 锁不可以共享
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

        for (int i = 1; i <= 5; i++) {
            String temp = String.valueOf(i);
            new Thread(() -> {
                myCache.put(temp, temp);
            }, temp).start();
        }
        for (int i = 1; i <= 5; i++) {
            String temp = String.valueOf(i);
            new Thread(() -> {
                myCache.get(temp);
            }, temp).start();
        }
    }

}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    public Object get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取中");
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取完成");
        return o;
    }

    public void put(String key, Object o) {
        System.out.println(Thread.currentThread().getName() + "写入中");
        map.put(key, o);
        System.out.println(Thread.currentThread().getName() + "写入完成");
    }
}

//带锁的缓存
class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();

    //读写锁
    ReadWriteLock lock = new ReentrantReadWriteLock();

    //写入,写入时,只希望同时只有一个线程执行
    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取中");
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }


    }

    public void put(String key, Object o) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入中");
            map.put(key, o);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }


    }
}