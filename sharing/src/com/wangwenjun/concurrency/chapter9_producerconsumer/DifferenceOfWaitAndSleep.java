package com.wangwenjun.concurrency.chapter9_producerconsumer;

import java.util.stream.Stream;

/**
 * sleep和wait的区别
 * 1.sleep是Thread的静态方法, wait是所有Object的方法
 * 2.sleep不会释放监视器(锁), 但是wait是会释放锁, 将线程放入监视器的wait set中
 * 3.sleep不需要依赖synchronize,但是wait需要
 * 4.sleep不需要被唤醒,但是wait需要(wait(10)除外)
 * @author malc
 * @create 2022/11/19 0019 22:21
 */
public class DifferenceOfWaitAndSleep {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("T1","T2").forEach(n -> {
            new Thread(() -> {
                m2();
            },n).start();
        });

    }

    public static void m1(){
        synchronized (LOCK) {
            try {
                System.out.println("线程:"+Thread.currentThread().getName()+"进入了...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2(){
        synchronized (LOCK) {
            try {
                System.out.println("线程:"+Thread.currentThread().getName()+"进入了...");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
