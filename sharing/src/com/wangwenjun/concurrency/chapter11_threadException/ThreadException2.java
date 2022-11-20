package com.wangwenjun.concurrency.chapter11_threadException;

/**
 * 捕获线程异常
 *
 * Runnable 的run方法没有抛出异常, 子类就不好抛出
 * @author malichun
 * @create 2022/11/20 0020 14:33
 */
public class ThreadException2 {
    public static void main(String[] args) {
        new Test1().test();
    }
}