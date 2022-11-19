package com.wangwenjun.concurrency.chapter7_synchronized;

/**
 * @author malc
 * @create 2022/11/19 0019 19:08
 */
public class SynchronizedStaticTest {


    public static void main(String[] args) {
        new Thread("T1"){
            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        }.start();
        new Thread("T2"){
            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        }.start();
        // 使用同一把锁

    }
}
