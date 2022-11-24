package com.wangwenjun.concurrent.chapter6_readwritelock;

/**
 * @author malichun
 * @create 2022/11/23 0023 19:10
 */
public class ReadWriteLock {
    // 当前几个线程在读
    private int readingReaders = 0; //
    // 有几个线程想读,但是读不了
    private int waitingReaders =0;
    // 当前几个线程在写(最多只有一个)
    private int writingWriters = 0;
    // 有多少个线程等着写
    private int waitingWriters = 0;
    private boolean preferWriter = true; // 偏爱writer

    public ReadWriteLock(){
        this(true);
    }

    public ReadWriteLock(boolean preferWriter){
        this.preferWriter = preferWriter;
    }
    //////////////////////////////////
    // 读锁
    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            while (writingWriters > 0 || (preferWriter&& waitingWriters >0)) {
                this.wait();
            }
            this.readingReaders++;
        }finally {
            this.waitingReaders--;
        }
    }

    // 释放锁
    public synchronized void readUnlock(){
        this.readingReaders--;
        this.notifyAll();
    }

    //////////////////////////////////
    // 写锁
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try{
            while(readingReaders > 0 || writingWriters >0){
                this.wait();
            }
            this.writingWriters++;
        }finally {
            this.waitingWriters--;
        }
    }

    // 释放锁
    public synchronized void writeUnLock(){
        this.writingWriters--;
        this.notifyAll();
    }

}
