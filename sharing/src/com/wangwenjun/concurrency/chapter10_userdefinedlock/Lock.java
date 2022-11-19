package com.wangwenjun.concurrency.chapter10_userdefinedlock;

import java.util.Collection;

/**
 * 自定义锁
 * @author malc
 * @create 2022/11/19 0019 23:17
 */
public interface Lock {

    class TimeOutException extends Exception{
        public TimeOutException(String message){
            super(message);
        }
    }

    /**
     * 上锁
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 上锁, 带时间的
     * @param mills
     * @throws InterruptedException
     * @throws TimeOutException
     */
    void lock(long mills) throws InterruptedException, TimeOutException;

    /**
     * 解锁
     */
    void unlock();

    /**
     * 查看有多少被阻塞的线程
     * @return
     */
    Collection<Thread> getBlockedThread();

    int getBlockedSize();

}
