package com.wangwenjun.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author malichun
 * @create 2022/12/11 0011 17:18
 */
public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    public void tryLock() throws GetLockException{
        boolean success = value.compareAndSet(0,1);
        if(!success){
            throw new GetLockException("Get the Lock failed");
        }else{
            lockedThread = Thread.currentThread();
        }
    }

    // 释放锁时, 谁拿的锁谁去释放
    public void unlock(){
        if(0 == value.get()){
            return;
        }

        // 是同一个线程才能释放当前锁
        if(lockedThread == Thread.currentThread() ) {
            value.compareAndSet(1, 0);
        }

    }
}
