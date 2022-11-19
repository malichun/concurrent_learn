package com.wangwenjun.concurrency.chapter4_daemon;

/**
 * 守护线程
 * @author malichun
 * @create 2022/11/19 0019 12:20
 */
public class DaemonThread {
    public static void main(String[] args) {
        // 在同一个ThreadGroup里面
        Thread t = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" running");
                Thread.sleep(100000);
                System.out.println(Thread.currentThread().getName()+" done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); // NEW
        // 设置守护线程
        t.setDaemon(true);

        t.start(); // RUNNABLE -> RUNNING| -> dead| -> blocked

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

}
/**
 * A <----------------------------------------> B
 *      ->daemonThread(health check)
 *
 *
 */
