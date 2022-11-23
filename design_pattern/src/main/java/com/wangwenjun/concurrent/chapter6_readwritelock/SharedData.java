package com.wangwenjun.concurrent.chapter6_readwritelock;

import java.util.Arrays;

/**
 * 共享数据
 * @author malichun
 * @create 2022/11/23 0023 19:22
 */
public class SharedData {
    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        // 初始化
        Arrays.fill(buffer, '*');
    }

    public char[] read() throws InterruptedException {
        try{
            lock.readLock();
            return this.doRead();
        }finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try{
            lock.writeLock();
            this.doWrite(c);
        }finally {
            lock.writeUnLock();
        }
    }

    private char[] doRead() {
        slowly(50);
        return Arrays.copyOf(buffer, buffer.length);
    }

    private void doWrite(char c) {
        Arrays.fill(buffer, c);
        slowly(10);
    }



    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
