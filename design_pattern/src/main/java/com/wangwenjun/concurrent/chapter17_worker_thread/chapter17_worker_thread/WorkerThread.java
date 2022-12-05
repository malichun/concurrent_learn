package com.wangwenjun.concurrent.chapter17_worker_thread.chapter17_worker_thread;

import java.util.Random;

/**
 * 供他人
 */
public class WorkerThread extends Thread{

    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }


    @Override
    public void run() {
        while(true){
            channel.take().execute();

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
