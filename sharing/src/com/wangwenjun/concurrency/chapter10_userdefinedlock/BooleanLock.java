package com.wangwenjun.concurrency.chapter10_userdefinedlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 自定义锁
 * @author malc
 * @create 2022/11/19 0019 23:22
 */
public class BooleanLock implements Lock{

    // initValue为true的时候, 表示锁被拿走了
    // initValue为false的时候, 可以抢锁了
    private boolean initValue;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock(){
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while(initValue){
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if(mills<=0){
            lock();
        }
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while(initValue){
            if(hasRemaining<=0){
                // 超时了
                throw new TimeOutException("超时了...");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = System.currentTimeMillis() - endTime;
        }
        //抢到锁了
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if(currentThread == Thread.currentThread()) {
            this.initValue = false;
            System.out.println(Thread.currentThread() + " 释放了lock monitor ");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection); // 只读操作
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
