package com.wangwenjun.juc.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author malichun
 * @create 2022/12/15 0015 21:22
 */
public class UnsafeTest {
    /**
     * Java是一种安全的编程语言，可以防止程序员犯很多愚蠢的错误，
     * 其中大部分基于内存管理。但是，有一种方法可以故意犯这样的错误，使用不安全类。
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // 通过反射
        //Unsafe unsafe = getUnsafe();
        //System.out.println(unsafe);

        ExecutorService service = Executors.newFixedThreadPool(100);
        //Counter counter = new StupidCounter();
        //Counter counter = new SyncCounter();
        //Counter counter = new LockCounter();
        //Counter counter = new AtomicCounter();
        Counter counter = new CasCounter();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            service.submit(new CounterRunnable(counter, 10000));
        }

        service.shutdown();// 让已经有的线程执行完成,并不接受新的任务了, 并不会阻塞当前线程
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Counter result:"+counter.getCounter());
        System.out.println("Time passed in ms: "+(end - start));
    }

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    interface Counter{

        void increment();

        long getCounter();
    }

    /**
     * stupidCounter
     */
    static class StupidCounter implements Counter{

        private long counter = 0;
        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     * 使用同步锁
     */
    static class SyncCounter implements Counter{

        private long counter = 0;
        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     * 使用原子
     */
    static class LockCounter implements Counter{

        private long counter = 0;
        ReentrantLock lock = new ReentrantLock();

        @Override
        public void increment() {
            lock.lock();
            try{
                counter++;
            }finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     *
     */
    static class AtomicCounter implements Counter{

        private AtomicLong counter = new AtomicLong();
        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.intValue();
        }
    }

    /**
     * 使用Unsafe
     */
    static class CasCounter implements Counter{
        private volatile long counter;
        private Unsafe unsafe;
        private long offset;

        CasCounter() throws Exception {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
        }
        @Override
        public void increment() {
            long current = counter;
            while(!unsafe.compareAndSwapLong(this, offset,current, current+1)){
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class CounterRunnable implements Runnable{
        private final  Counter counter;
        private final int num;

        CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }

}
