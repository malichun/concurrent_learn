package com.wangwenjun.juc.atomic;

/**
 * @author malichun
 * @create 2022/12/11 0011 17:07
 */
public class AtomicIntegerDetailTest2 {

    private final static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    //doSomething();
                    doSomething2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (GetLockException e) {
                    e.printStackTrace();
                }
            }).start();
        }



    }

    /**
     * 使用synchronized, 一个线程占着锁,会导致其他线程一直等待
     * @throws InterruptedException
     */
    private static void doSomething() throws InterruptedException {
        synchronized (AtomicIntegerDetailTest2.class){
            System.out.println(Thread.currentThread().getName()+"get the lock");
            Thread.sleep(1000000);
        }
    }

    /**
     * 获取不到锁就不等
     * 使用无锁
     * @throws InterruptedException
     */
    private static void doSomething2() throws InterruptedException, GetLockException {

        try{
            lock.tryLock(); // 抢不到就抛出去
            System.out.println(Thread.currentThread().getName()+"get the lock");
            Thread.sleep(1000000);
        }finally {
            lock.unlock();
        }
    }
}
