package com.wangwenjun.concurrency.chapter7_synchronized;

/**
 * @author malc
 * @create 2022/11/19 0019 18:13
 */
public class SynchronizedTest {
    private final static Object LOCK = new Object();
    public static void main(String[] args) {
        Runnable runnable = () ->{
            synchronized (LOCK){
                try {
                    Thread.sleep(200_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }
}
