package com.wangwenjun.concurrent.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author malichun
 * @create 2022/11/21 0021 23:37
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    private static void work(){
        synchronized (LOCK){
            System.out.println("开始.....");
            try {
                System.out.println("线程进来了");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程将退出");
        }
    }

    /**
     * 1.所有的对象都会有一个waitset,用来存放调用了该对象wait方法之后进入BLOCK状态线程
     * 2.线程被notify之后,不一定立即得到执行
     * 3.线程从wait set中被唤醒的顺序不一定是 FIFO
     * 4.线程被唤醒后,必须重新获取锁(进入EntryList然后取争抢锁)
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(WaitSet::work).start();
        Thread.sleep(1000);
        synchronized (LOCK) {
            LOCK.notify();
        }

        //IntStream.rangeClosed(1, 10).forEach(i -> {
        //        new Thread(String.valueOf(i)){
        //            @Override
        //            public void run() {
        //                synchronized (LOCK){
        //                    try {
        //                        Optional.of(Thread.currentThread().getName()+" 进入waitset")
        //                            .ifPresent(System.out::println);
        //                        LOCK.wait(); // BLOCKED, 会释放锁, 让别的程序进来
        //                        Optional.of(Thread.currentThread().getName()+" 离开waitset")
        //                            .ifPresent(System.out::println);
        //                    } catch (InterruptedException e) {
        //                        e.printStackTrace();
        //                    }
        //                }
        //            }
        //        }.start();
        //    });
        //
        //Thread.sleep(3000);
        //
        //IntStream.rangeClosed(1,10).forEach(i -> {
        //    synchronized (LOCK){
        //        LOCK.notify();
        //        try {
        //            Thread.sleep(1000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //});

    }
}
