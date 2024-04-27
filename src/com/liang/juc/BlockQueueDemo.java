package com.liang.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueDemo {

    public static void main(String[] args) {
        blockQueueTest();
    }

    private static void blockQueueTest() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));

        arrayBlockingQueue.remove();
        System.out.println(arrayBlockingQueue.add("d"));
    }
}
