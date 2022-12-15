package com.wangwenjun.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author malichun
 * @create 2022/12/15 0015 19:34
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        final AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                final int MAX = 20;
                for (int j = 0; j < MAX; j++) {
                    int v = updater.getAndIncrement(me);
                    System.out.println(Thread.currentThread().getName() + " => "+v);
                }

            }).start();
        }
    }

    static class TestMe{
        volatile int i;

    }
}
