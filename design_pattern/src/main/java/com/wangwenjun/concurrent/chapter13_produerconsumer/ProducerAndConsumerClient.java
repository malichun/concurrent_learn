package com.wangwenjun.concurrent.chapter13_produerconsumer;

import java.util.stream.IntStream;

/**
 * @author malichun
 * @create 2022/11/26 0026 21:52
 */
public class ProducerAndConsumerClient {
    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue(100);


        IntStream.rangeClosed(0,5).forEach(i -> {
            new ProducerThread(messageQueue, i).start();
        });

        IntStream.rangeClosed(0,6).forEach(i -> {
            new ConsumerThread(messageQueue, i).start();
        });

    }
}
