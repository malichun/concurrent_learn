package com.wangwenjun.concurrency.chapter4;

/**
 * @author malichun
 * @create 2022/11/19 0019 12:40
 */
public class DaemonThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                try {
                    while(true){
                        System.out.println("做一些心跳的检测....");
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("内部线程是否daemon: "+innerThread.isDaemon());
            //innerThread.setDaemon(true);
            innerThread.start();
            try {
                Thread.sleep(1_000);
                System.out.println("T 线程完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T线程");

        t.setDaemon(true); // main线程结束程序就结束了

        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main线程结束");
    }
}
