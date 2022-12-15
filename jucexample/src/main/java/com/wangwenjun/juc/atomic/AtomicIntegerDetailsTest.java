package com.wangwenjun.juc.atomic;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author malichun
 * @create 2022/12/11 0011 16:10
 */
public class AtomicIntegerDetailsTest {

    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get()); // 0

        i = new AtomicInteger(10);
        System.out.println(i.get()); // 10

        // set
        i.set(12);
        System.out.println(i.get()); // 12

        i.lazySet(15);
        System.out.println(i.get()); // 15

        // getAndSet
        AtomicInteger getAndSet = new AtomicInteger(10);
        int result = getAndSet.getAndAdd(10);
        System.out.println(result); // 10
        System.out.println(getAndSet.get()); // 20

        ////////////////////////////////////////////////////

        final AtomicInteger getAndSet2 = new AtomicInteger();
        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                int v = getAndSet2.addAndGet(1);
                System.out.println(v);
            }
        }).start();

        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                int v = getAndSet2.addAndGet(1);
                System.out.println(v);
            }
        }).start();

        ///////////////////////////
        AtomicInteger i2 = new AtomicInteger(10);
        boolean flag = i2.compareAndSet(12,20);
        System.out.println(i2.get()); // 10
        System.out.println(flag);  // false

    }

}
