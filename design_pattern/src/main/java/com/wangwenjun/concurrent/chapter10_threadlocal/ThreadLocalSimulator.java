package com.wangwenjun.concurrent.chapter10_threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 模拟ThreadLocal
 * 始终以当前线程作为key值
 */
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t){
        synchronized (this){
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }

    public T get(){
        synchronized (this){
            Thread key = Thread.currentThread();
            T value =  storage.get(key);
            if(value == null){
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue(){
        return null;
    }
}

class ThreadLocalSimulatorTest{
    private final static ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<String>(){
        @Override
        public String initialValue() {
            return "No Value";
        }
    };

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " "+ threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " "+ threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("==================");
        System.out.println(Thread.currentThread().getName() + " "+ threadLocal.get());
    }
}
