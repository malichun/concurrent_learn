package com.wangwenjun.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author malichun
 * @create 2022/12/15 0015 20:28
 */
public class AtomicIntegerFieldUpdaterTest {

    private volatile int i;

    // 使用原子
    private AtomicInteger j = new AtomicInteger();

    private AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater
        = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "i");

    public void update(int newValue){
        updater.compareAndSet(this, i, newValue);
    }

    public int get(){
        return i;
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterTest test = new AtomicIntegerFieldUpdaterTest();
        test.update(10);
        System.out.println(test.get());
    }
}
