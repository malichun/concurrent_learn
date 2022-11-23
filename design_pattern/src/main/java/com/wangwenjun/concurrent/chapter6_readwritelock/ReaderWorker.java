package com.wangwenjun.concurrent.chapter6_readwritelock;

/**
 * @author malichun
 * @create 2022/11/23 0023 20:00
 */
public class ReaderWorker extends Thread{

    private final SharedData data;

    public ReaderWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuffer = data.read();
                System.out.println(Thread.currentThread().getName()+" reads "+ String.valueOf(readBuffer));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
