package com.wangwenjun.concurrent.chapter10_threadlocal;

import java.util.Random;

/**
 * @author malichun
 * @create 2022/11/26 0026 16:01
 */
public class ThreadLocalComplexTest {
    private final static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " "+ threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " "+ threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("==================");
        System.out.println(Thread.currentThread().getName() + " "+ threadLocal.get());
    }
}
