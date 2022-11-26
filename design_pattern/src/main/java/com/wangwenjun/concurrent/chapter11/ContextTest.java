package com.wangwenjun.concurrent.chapter11;

import java.util.stream.IntStream;

/**
 * @author malichun
 * @create 2022/11/26 0026 16:55
 */
public class ContextTest {
    public static void main(String[] args) {

        IntStream.rangeClosed(1,5)
            .forEach(i -> {
                new Thread(new ExecutionTask()).start();
            });
    }
}
