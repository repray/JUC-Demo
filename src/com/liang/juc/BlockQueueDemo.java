package com.liang.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {


        /**
         * 抛出异常
         */
//        blockQueueTest();
        /**
         * 有返回值,不抛出异常
         */
//        blockQueueTest2();
        /**
         * 等待,一直阻塞
         */
//        blockQueueTest3();
        /**
         * 等待,阻塞(等待超时)
         */
        blockQueueTest4();
    }

    private static void blockQueueTest() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        //在队列已满时,执行add操作,会报IllegalStateException: Queue full
        System.out.println(arrayBlockingQueue.add("d"));

        System.out.println("-------------------------");
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        //在队列为空时,执行remove操作 会报NoSuchElementException
        System.out.println(arrayBlockingQueue.remove());
        //查看队列首位元素,element在队列为空时会抛出异常 NoSuchElementException
        System.out.println(arrayBlockingQueue.element());

        System.out.println(arrayBlockingQueue.add("d"));
    }

    private static void blockQueueTest2() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        /**
         * offer方法,类似add,在队列已满时,继续执行添加,不会报错,会返回false
         */
        System.out.println(arrayBlockingQueue.offer("a")); //true
        System.out.println(arrayBlockingQueue.offer("b")); //true
        System.out.println(arrayBlockingQueue.offer("c")); //true
        System.out.println(arrayBlockingQueue.offer("d")); //false
        System.out.println("-------------------------");

        //类似于remove,有返回值,不抛出异常
        System.out.println(arrayBlockingQueue.poll()); //a

        System.out.println(arrayBlockingQueue.poll()); //b
        System.out.println(arrayBlockingQueue.poll()); //c
        System.out.println(arrayBlockingQueue.poll()); //null
        System.out.println("---------------------------");
//        System.out.println(arrayBlockingQueue.element());
        //查看队列首位元素,peek不会抛出异常
        System.out.println(arrayBlockingQueue.peek());
    }

    private static void blockQueueTest3() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        //在队列已满插入新元素时会一直阻塞
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        //在队列为空,试图取出新元素时会一直阻塞
        System.out.println(arrayBlockingQueue.take());

    }

    private static void blockQueueTest4() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        arrayBlockingQueue.offer("a");
        arrayBlockingQueue.offer("b");
        arrayBlockingQueue.offer("c");
        //等待,超过2秒就退出
        arrayBlockingQueue.offer("d", 2, TimeUnit.SECONDS);

        System.out.println("==================================");
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        //等待,超过2秒就退出
        arrayBlockingQueue.poll(2, TimeUnit.SECONDS);
    }

}
