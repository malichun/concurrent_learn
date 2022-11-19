package com.wangwenjun.concurrency.chapter3;

/**
 * 线程名字
 * @author malichun
 * @create 2022/11/17 0017 22:59
 */
public class CreateThread {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread(() -> System.out.println("========"));
        t1.start();
        t2.start();
        System.out.println(t1.getName()); // Thread-0
        System.out.println(t2.getName()); // Thread-1

        Thread t3 = new Thread("MyName");
        Thread t4 = new Thread(() -> {
            System.out.println("Runnable....");
        });

        System.out.println(t3.getName()); // MyName
        System.out.println(t4.getName()); // Thread-02

        Thread t5 = new Thread(() -> {
            System.out.println("Runnable...."+Thread.currentThread().getName());
        }, "RunnableThread");
        t5.start();
        System.out.println(t5.getName()); // RunnableThread
    }
}
