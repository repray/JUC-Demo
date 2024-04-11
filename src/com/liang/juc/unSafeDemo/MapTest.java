package com.liang.juc.unSafeDemo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        //默认等价于 new HashMap<>(16,0.75)
        /**
         * 并发解决方案:
         * new ConcurrentHashMap<>();
         * Collections.synchronizedMap(new ConcurrentHashMap<>())
         */
        Map<String, String> map = Collections.synchronizedMap(new ConcurrentHashMap<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
