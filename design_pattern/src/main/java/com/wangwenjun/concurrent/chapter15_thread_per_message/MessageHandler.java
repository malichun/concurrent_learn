package com.wangwenjun.concurrent.chapter15_thread_per_message;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author malichun
 * @create 2022/12/05 0005 20:22
 */
public class MessageHandler {

    private final static Random random = new Random(System.currentTimeMillis());

    private final static Executor EXECUTOR = Executors.newFixedThreadPool(5);

    public void request(Message message){
        //new Thread(() -> {
        //   String value = message.getValue();
        //    try {
        //        Thread.sleep(random.nextInt(1000));
        //        System.out.println("消息被处理, "+value);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //});
        EXECUTOR.execute(() -> {
            String value = message.getValue();
                try {
                    Thread.sleep(random.nextInt(1000));
                    System.out.println("消息被处理, "+Thread.currentThread().getName()+"  "+value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });

    }

    public void shutdown(){
        ((ExecutorService)EXECUTOR).shutdown();
    }
}
