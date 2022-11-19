package com.wangwenjun.concurrency.chapter6;


/**
 * 结束线程
 * 使用标记
 * @author malc
 * @create 2022/11/19 0019 15:39
 */
public class ThreadCloseGraceful {

    private static class Worker extends Thread{
        private volatile boolean start = true;

        @Override
        public void run() {
            while(start){
                //
            }
        }

        public void shutdown(){
            start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
    }
}
