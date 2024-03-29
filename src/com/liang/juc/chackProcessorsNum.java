package com.liang.juc;

public class chackProcessorsNum {
    public static void main(String[] args) {
        //获取cpu的线程 数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
