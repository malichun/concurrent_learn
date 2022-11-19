package com.wangwenjun.concurrency.chapter5_join;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author malc
 * @create 2022/11/19 0019 14:00
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1,1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });
        Thread t2 = new Thread(() -> {
            IntStream.range(1,1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });

        t1.start();
        t2.start();

        t1.join(); // 等待当前线程结束
        t2.join();
        // main线程会等待t1,t2两个线程结束再执行
        Optional.of("所有的task结束了.").ifPresent(System.out::println);
        //
        //IntStream.range(1,1000)
        //    .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
    }
}
