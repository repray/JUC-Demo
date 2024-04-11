package com.liang.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * java.util.ConcurrentModificationException 并发修改异常
 */
public class ListTest {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1", "2", "3");
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();

        }
    }
}
