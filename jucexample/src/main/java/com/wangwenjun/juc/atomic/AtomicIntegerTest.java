package com.wangwenjun.juc.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author malichun
 * @create 2022/12/11 0011 1:48
 */
public class AtomicIntegerTest {

    static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    /**
     * volatile
     * 1.保证可见性
     * 2.顺序性
     * 3.不能保证原子性
     */
    private static volatile int value;

    public static void main(String[] args) throws InterruptedException {
        // Thread t1 = new Thread(() -> {
        //     int x = 0;
        //     while(x<500){
        //         int tmp = value;
        //         System.out.println(Thread.currentThread().getName()+":"+tmp);
        //         value+=1;
        //         /**
        //          * 单一的步骤是原子性的
        //          * value = value + 1
        //          * (1) 从主存获取到本地内存
        //          * (2) 执行add 1 => x
        //          * (3) 分配值到x
        //          * (4) 刷进主存
        //          */
        //         x++;
        //         set.add(tmp);
        //     }
        // });
        //Thread t2 = new Thread(() -> {
        //    int x = 0;
        //    while(x<500){
        //        int tmp = value;
        //        System.out.println(Thread.currentThread().getName()+":"+tmp);
        //        value+=1;
        //        x++;
        //        set.add(tmp);
        //    }
        //});
        //Thread t3 = new Thread(() -> {
        //    int x = 0;
        //    while(x<500){
        //        int tmp = value;
        //        System.out.println(Thread.currentThread().getName()+":"+tmp);
        //        value+=1;
        //        x++;
        //        set.add(tmp);
        //    }
        //});
        //t1.start();
        //t2.start();
        //t3.start();
        //t1.join();
        //t2.join();
        //t3.join();

        //System.out.println(set.size());

        final AtomicInteger value = new AtomicInteger();
        IntStream.range(0, 5)
            .mapToObj(index -> {
                Thread t = new Thread(() -> {
                    int x = 0;
                    while (x < 500) {
                        int v = value.getAndIncrement();
                        set.add(v);
                        System.out.println(Thread.currentThread().getName() + ":" + v);
                        x++;
                    }
                }, "Thread-" + index);
                t.start();
                return t;
            })
            .forEach(t -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        System.out.println(set.size());


    }
}
