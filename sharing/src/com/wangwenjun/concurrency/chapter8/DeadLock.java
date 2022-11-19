package com.wangwenjun.concurrency.chapter8;

/**
 * @author malc
 * @create 2022/11/19 0019 19:17
 */
public class DeadLock {
    private OtherService otherService;

    public DeadLock(OtherService otherService){
        this.otherService = otherService;
    }

    private final Object lock = new Object();

    public void m1(){
        synchronized (lock){
            System.out.println("m1");
            otherService.s1();
        }
    }

    public void m2(){
        synchronized (lock){
            System.out.println("m2");
            otherService.s2();
        }
    }

}
