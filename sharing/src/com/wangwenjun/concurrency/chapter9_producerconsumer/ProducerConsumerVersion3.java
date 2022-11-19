package com.wangwenjun.concurrency.chapter9_producerconsumer;

import java.util.stream.Stream;

/**
 * @author malc
 * @create 2022/11/19 0019 21:47
 */
public class ProducerConsumerVersion3 {

    private int i = 0;

    final private Object LOCK = new Object();
    //final private Object

    private volatile boolean isProduced = false; // 生产了

    public void produce() {
        synchronized (LOCK) {
            while(isProduced){
                try {
                    LOCK.wait(); //
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 生产
            i++;
            System.out.println("P->" + i);
            // 生产后要通知可以消费
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    public void consume() {
        synchronized (LOCK) {
            while(!isProduced){
                // 没有生产, 则等
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C->" + i);
            LOCK.notifyAll(); // 已经消费了, 可以再进行生产了
            isProduced = false;
        }
    }


    public static void main(String[] args) {
        // 只有一个生产者和消费者的情况, 多线程会有问题
        ProducerConsumerVersion3 producerConsumerVersion3 = new ProducerConsumerVersion3();
        Stream.of("p1", "p2","p3").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    producerConsumerVersion3.produce();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, n).start();
        });

        Stream.of("c1", "c2","c3").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    producerConsumerVersion3.consume();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, n).start();
        });


    }
}
