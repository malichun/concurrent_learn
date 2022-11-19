package com.wangwenjun.concurrency.chapter7_synchronized;

/**
 * @author malc
 * @create 2022/11/19 0019 19:06
 */
public class SynchronizedStatic {
    static {
        synchronized (SynchronizedStatic.class){
            System.out.println("static "+ Thread.currentThread().getName());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1(){
        System.out.println("m1 "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2(){
        System.out.println("m2 "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
