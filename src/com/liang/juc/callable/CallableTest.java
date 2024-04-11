package com.liang.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread(new MyThread()).start();
        MyCallableThread thread = new MyCallableThread();
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask,"callable").start();
        //这个get方法可能会产生阻塞
        System.out.println(String.valueOf(futureTask.get()));
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("线程启动");
    }
}

class MyCallableThread implements Callable<String> {

    @Override
    public String call() {
        //callable结果会被缓存
        System.out.println("callable线程启动");
        return "1024";
    }
}