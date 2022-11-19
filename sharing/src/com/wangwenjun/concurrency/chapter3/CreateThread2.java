package com.wangwenjun.concurrency.chapter3;

import java.util.Arrays;

/**
 * @author malichun
 * @create 2022/11/18 0018 0:05
 */
public class CreateThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        //System.out.println(t.getThreadGroup()); // java.lang.ThreadGroup[name=main,maxpri=10]
        //System.out.println(Thread.currentThread().getName()); // main
        //System.out.println(Thread.currentThread().getThreadGroup().getName()); // 线程组: main

        ThreadGroup threadGroup = t.getThreadGroup();

        ThreadGroup threadGroup1 = Thread.currentThread().getThreadGroup();

        System.out.println(threadGroup == threadGroup1); // True

        int activeCount = threadGroup.activeCount();
        Thread[] threads = new Thread[activeCount];

        threadGroup.enumerate(threads);

        Arrays.stream(threads).forEach(System.out::println);
        // 结果
        // Thread[main,5,main]
        //Thread[Monitor Ctrl-Break,5,main]
        //Thread[Thread-0,5,main]

        Arrays.stream(threads).forEach(thread -> {
            System.out.println("thread: "+ thread.getName() +" daemon:"+ thread.isDaemon());
        });
        //thread: main daemon:false
        //thread: Monitor Ctrl-Break daemon:true
        //thread: Thread-0 daemon:false

        

    }
}
