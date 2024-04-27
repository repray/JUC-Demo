package com.liang.juc;

import java.util.concurrent.*;

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
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//创建单例线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定大小的线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();//创建一个可变线程池

        // 使用ThreadPoolExecutor创建自定义线程池
        /**
         * 最大线程数该如何定义(调优)
         * 1.cpu密集型 根据CPU核心数定义最大线程数,可以保持cpu的效率最高
         * 2.io密集型 最大线程数需要大于程序中占用io资源多的任务数
         */
        //获取当前cpu运行核心数
        System.out.println("当前cpu运行核心数:"+Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                Runtime.getRuntime().availableProcessors(),
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        /**
         *  AbortPolicy 默认拒绝策略,阻塞队列已满,最大线程数已满,不执行,抛出异常
         *  CallerRunsPolicy 哪个提交的被拒绝任务,给哪个执行,不抛出异常 举例 main线程提交的,则main线程执行
         *  DiscardPolicy 队列满了,丢掉任务,不执行,不抛出异常
         *  DiscardOldestPolicy 队列满了,尝试去和最早的竞争,也不会抛出异常
         */

        try {
            for (int i = 1; i <= 9; i++) {
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
