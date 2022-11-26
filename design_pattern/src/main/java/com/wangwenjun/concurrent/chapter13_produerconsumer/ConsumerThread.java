package com.wangwenjun.concurrent.chapter13_produerconsumer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author malichun
 * @create 2022/11/26 0026 21:49
 */
public class ConsumerThread extends Thread{
    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());


    public ConsumerThread(MessageQueue messageQueue,int seq){
        super("CONSUMER"+ seq);
        this.messageQueue= messageQueue;

    }

    @Override
    public void run() {
        while(true){
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName()+" get message "+message);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
