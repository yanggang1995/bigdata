package com.yg.thread;

import java.util.concurrent.TimeUnit;

/**
 * 模拟死锁的情况
 *
 * @author Y.G
 * @date 2020/7/17 10:06
 **/
public class DeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("--- lock1 被获取");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("--- lock2 被获取");
                }
            }
        }, "lock1");
        Thread t2 = new Thread(()->{
            synchronized (lock2) {
                System.out.println("===== lock2 被获取");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("=== lock1 被获取");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
