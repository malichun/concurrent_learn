package com.wangwenjun.concurrency.chapter6_interrupt;

/**
 * 打断
 *
 * @author malc
 * @create 2022/11/19 0019 14:49
 */
public class ThreadInterrupt {

    private static Object MONITOR = new Object();
    public static void main(String[] args) {
       /*  Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //try {
                    //    Thread.sleep(10);
                    //} catch (InterruptedException e) {
                    //    System.out.println("收到打断信号");
                    //    e.printStackTrace();
                    //}
                    //System.out.println(">>" + this.isInterrupted());
                    synchronized (MONITOR){
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println(isInterrupted());
                        }
                    }
                }
            }
        };

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted()); // false
        t.interrupt();
        System.out.println(t.isInterrupted());  // true */
/*
        Thread t = new Thread(() -> {
            while (true){
                synchronized (MONITOR){
                    try {
                        MONITOR.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.interrupted());
                    }
                }
            }
        });
        t.start(); */

        final Thread t = new Thread(() -> {
            while (true){

            }
        });

        t.start();
        Thread main = Thread.currentThread();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                main.interrupt(); // 打断
                System.out.println("t2执行xx的interrupt方法");
            }
        };
        t2.start();

        try {
            t.join(); // join的main线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
