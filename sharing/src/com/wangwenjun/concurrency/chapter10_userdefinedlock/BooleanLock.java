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
        while(initValue){ // 开始为false
            if(hasRemaining<=0){
                // 超时了
                throw new TimeOutException("超时了...");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills); // 其他线程到这儿后就会等着, 释放锁
            hasRemaining = endTime -  System.currentTimeMillis();
        }
        //抢到锁了
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if(currentThread == Thread.currentThread()) {
            this.initValue = false;
            System.out.println(Thread.currentThread().getName() + " 释放了lock monitor ");
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
