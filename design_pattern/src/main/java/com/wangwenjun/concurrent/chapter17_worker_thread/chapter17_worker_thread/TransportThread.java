package com.wangwenjun.concurrent.chapter17_worker_thread.chapter17_worker_thread;

import java.util.Random;

/**
 * 装配工人
 */
public class TransportThread extends Thread{
    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name,Channel channel) {
        super(name);
        this.channel = channel;
    }

    // 装配工人运行
    @Override
    public void run() {
        try{
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1000));
            }
        }catch (Exception e){

        }
    }
}
