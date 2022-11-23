package com.wangwenjun.concurrent.chapter6_readwritelock;

/**
 * ReadWriteLock design pattern
 * Read-Write design pattern
 */
public class ReadWriteLockClient {
    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new WriterWorker(sharedData,"qwertyuiopasdfg").start();
        new WriterWorker(sharedData,"QWERTYUIOPASDFG").start();
    }
}
