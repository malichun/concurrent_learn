package com.wangwenjun.concurrent.chapter15_thread_per_message;

import java.util.stream.IntStream;

/**
 * @author malichun
 * @create 2022/12/05 0005 20:24
 */
public class PerThreadClient {
    public static void main(String[] args) {
        MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(0, 10)
            .forEach(i -> handler.request(new Message(String.valueOf(i))));
        handler.shutdown();
    }

}
