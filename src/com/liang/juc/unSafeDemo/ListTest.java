package com.liang.juc.unSafeDemo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * java.util.ConcurrentModificationException 并发修改异常
 */
public class ListTest {
    public static void main(String[] args) {
    /**
     * 并发下,arraylist不安全
     * 解决方案:
     * 1. List<String> list = new Vector<>();
     * 2. List<String> list = Collections.synchronizedList(new ArrayList<>());
     * 3.  List<String> list = new CopyOnWriteArrayList();
     */
        /**
         * CopyOnWrite COW 写入时复制
         */
        List<String> list = new CopyOnWriteArrayList();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();

        }
    }
}
