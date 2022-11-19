package com.wangwenjun.concurrency.chapter4;

import java.util.Optional;

/**
 * @author malichun
 * @create 2022/11/19 0019 12:53
 */
public class ThreadSimpleApi {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() ->{
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");

        t.start();

        Optional.of(t.getName()).ifPresent(System.out::println); // t1
        Optional.of(t.getId()).ifPresent(System.out::println); //20
        Optional.of(t.getPriority()).ifPresent(System.out::println); // 5


        Thread.sleep(10000);
    }
}
