package com.wangwenjun.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author malichun
 * @create 2022/12/14 0014 13:20
 */
public class AtomicBooleanFlag {
    private final static AtomicBoolean flag = new AtomicBoolean(true);

    //private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while(flag.get()){
                try {
                    Thread.sleep(1000);
                    System.out.println("i am working.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("I am finished.");
        }).start();

        Thread.sleep(1000);

        //Thread.sleep(5000);
         flag.set(false);
        //flag = false;
    }
}
