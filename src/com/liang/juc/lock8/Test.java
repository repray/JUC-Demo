package com.liang.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 锁的是方法的使用者,举例 phone.call()和sendSMS()方法,由于call()方法加锁,在两个线程都是使用phone对象调用call()和sendSMS()方法时,加锁的是phone对象,所以只能有一个方法先抢占到锁
 * 在将call()和sendSMS()方法都设置为static时,由于static方法加锁,锁的是调用者的class对象,所以此时,也是只有一把锁(phone.class锁),此时也是按顺序进行
 * 在讲call()方法设置为static,sendSMS()不加时,则此时有两把锁,一把是Phone phone = new Phone() 产生的phone对象的锁,一把是phone.class的锁,所以此时输出结果是谁先执行谁先输出
 */
public class Test {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(
                () -> {
                    phone.call();
                }, "A"
        ).start();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(
                () -> {
                    phone.sendSMS();
                }, "B"
        ).start();
    }
}

class Phone {
    public synchronized void call() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("打电话");
    }

    public synchronized void sendSMS() {
        System.out.println("发短信");
    }
}
