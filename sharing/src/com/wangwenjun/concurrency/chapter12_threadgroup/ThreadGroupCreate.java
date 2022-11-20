package com.wangwenjun.concurrency.chapter12_threadgroup;

import java.util.Arrays;

/**
 * 创建线程组
 *
 * main线程的线程组是main
 * @author malichun
 * @create 2022/11/20 0020 15:13
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
        ////////////////////////////////
        // 1.使用名字
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while(true){
                    try {
                        //System.out.println(getThreadGroup().getName()); // TG1
                        //System.out.println(getThreadGroup().getParent()); // java.lang.ThreadGroup[name=main,maxpri=10]
                        //System.out.println(getThreadGroup().getParent().activeCount()); // 3
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

        System.out.println(t1.getThreadGroup()); // TG1


        ////////////////////////////////
        // 2.使用父线程组
      /*   ThreadGroup tg2 = new ThreadGroup(tg1, "TG2"); // TG1
        System.out.println(tg2.getName()); // TG2
        System.out.println(tg2.getParent()); // java.lang.ThreadGroup[name=TG1,maxpri=10] */

        ThreadGroup tg2 = new ThreadGroup("TG2");
        Thread t2 = new Thread(tg2,"T2"){
            @Override
            public void run() {
                System.out.println("-----"+tg1.getName()); // TG1
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.stream(threads).forEach(thread -> {
                    System.out.println(thread);  // Thread[t1,5,TG1]
                });
            }
        };
        t2.start();


       // System.out.println(Thread.currentThread().getName()); // main
       //  System.out.println(Thread.currentThread().getThreadGroup().getName()); // main

    }
}
