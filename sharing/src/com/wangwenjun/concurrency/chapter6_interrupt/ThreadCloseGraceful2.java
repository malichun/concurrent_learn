package com.wangwenjun.concurrency.chapter6_interrupt;

/**
 * 中断线程, 使用interrupt()
 * @author malc
 * @create 2022/11/19 0019 15:45
 */
public class ThreadCloseGraceful2 {

    private static class Worker extends Thread{
        @Override
        public void run() {
            while(true) {
                //try {
                //    Thread.sleep(1);
                //} catch (InterruptedException e) {
                //    e.printStackTrace();
                //    break; // 退出循环
                //}
                if(Thread.interrupted()) break;
            }
            // ----------
            // 做其他事情
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打断其他线程
        worker.interrupt();

    }
}
