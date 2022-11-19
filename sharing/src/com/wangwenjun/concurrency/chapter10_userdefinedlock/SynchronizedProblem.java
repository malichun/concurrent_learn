package com.wangwenjun.concurrency.chapter10_userdefinedlock;

/**
 * synchronized机制
 * @author malc
 * @create 2022/11/19 0019 23:58
 */
public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        }.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(){
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        };
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
        System.out.println(t2.isInterrupted()); // 就算打断线程原线程还是会执行的
    }

    private synchronized static void run(){
        System.out.println(Thread.currentThread());
        while(true){
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
