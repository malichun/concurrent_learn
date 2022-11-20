package com.wangwenjun.concurrency.chapter12_threadgroup;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author malichun
 * @create 2022/11/20 0020 15:32
 */
public class ThreadGroupAPI {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tg1.setDaemon(true); //如果设置成守护的方式, 只有里面线程执行结束后变成destory状态
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");
        Thread t2 = new Thread(tg2, "T2"){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };

        t2.start();

        System.out.println(tg1.activeCount()); // 2
        System.out.println(tg1.activeGroupCount()); // 活着的group数目, tg2
        t2.checkAccess(); // main线程是否可以修改t2的线程
        // tg1.destroy(); // 销毁线程组, 如果线程组不是空或者已经被销毁了, 则会抛出异常

        System.out.println("=============================");
        Thread[] ts1 = new Thread[tg1.activeCount()];
        tg1.enumerate(ts1);
        Arrays.stream(ts1).forEach(System.out::println);
        // Thread[t1,5,TG1]
        //Thread[T2,5,TG2]
        System.out.println("=============================");
        tg1.enumerate(ts1, false); // recurse:递归的意思
        Arrays.stream(ts1).forEach(System.out::println);
        // Thread[t1,5,TG1]
        //Thread[T2,5,TG2]

        System.out.println("=============================  会递归拿到所有的");
        // 获取main线程下面的线程组
        ts1 = new Thread[10];
        Thread.currentThread().getThreadGroup().enumerate(ts1, true);
        Arrays.stream(ts1).filter(Objects::nonNull).forEach(System.out::println);
        //Thread[main,5,main]
        //Thread[Monitor Ctrl-Break,5,main]
        //Thread[t1,5,TG1]
        //Thread[T2,5,TG2]

        System.out.println("============================= 只拿当前的");
        // 获取main线程下面的线程组
        ts1 = new Thread[10];
        Thread.currentThread().getThreadGroup().enumerate(ts1, false); // 只拿当前的
        Arrays.stream(ts1).filter(Objects::nonNull).forEach(System.out::println);
        //Thread[main,5,main]
        //Thread[Monitor Ctrl-Break,5,main]


        // tg1.interrupt(); // tg1, tg2线程组都中断了


    }
}
