package com.wangwenjun.concurrency.chapter9_producerconsumer;

import java.util.stream.Stream;

/**
 * 只有一个生产者和消费者的情况
 * @author malc
 * @create 2022/11/19 0019 20:40
 */
public class ProducerConsumerVersion2 {

    private int i = 0;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false; // 生产了

    public void produce(){
        synchronized (LOCK){
            if(isProduced){
                try {
                    LOCK.wait(); //
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                // 生产
                i++;
                System.out.println("P->"+i);
                // 生产后要通知可以消费
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consume(){
        synchronized (LOCK){
            if(isProduced){
                System.out.println("C->"+i);
                LOCK.notify(); // 已经消费了, 可以再进行生产了
                isProduced = false;
            }else{
                // 没有生产, 则等
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        // 只有一个生产者和消费者的情况, 多线程会有问题
        ProducerConsumerVersion2 producerConsumerVersion2 = new ProducerConsumerVersion2();
        Stream.of("p1","p2").forEach(n -> {
            new Thread(() ->{
                while(true) {
                    producerConsumerVersion2.produce();
                }
            },n).start();
        });

        Stream.of("c1","c2").forEach(n -> {
            new Thread(() ->{
                while(true) {
                    producerConsumerVersion2.consume();
                }
            },n).start();
        });




    }
}
