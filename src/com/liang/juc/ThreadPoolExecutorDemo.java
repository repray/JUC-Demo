package com.liang.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 池化技术
 * 程序的运行,本质是占用系统的资源并运行
 * 优化资源的使用 => 池化技术
 * 线程池 连接池 内存池 对象池 都是池化技术的应用
 * <p>
 * 线程池的好处:
 * 1.降低资源的消耗 (创建,销毁十分浪费资源)
 * 2.提高相应的速度
 * 3.方便管理
 * 线程复用,控制最大并发数,管理线程
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//创建单例线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定大小的线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();//创建一个可变线程池

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }
}
