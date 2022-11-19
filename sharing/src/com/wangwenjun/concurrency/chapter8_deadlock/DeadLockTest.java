package com.wangwenjun.concurrency.chapter8_deadlock;

/**
 * @author malc
 * @create 2022/11/19 0019 20:11
 */
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);
        new Thread(){
            @Override
            public void run() {
                while(true){
                    deadLock.m1();
                }
            }
        }.start();

        new Thread(() ->{
          while(true){
              otherService.s2();
          }
        }).start();
    }
}
