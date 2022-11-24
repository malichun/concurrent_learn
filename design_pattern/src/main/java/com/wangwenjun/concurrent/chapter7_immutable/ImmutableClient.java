package com.wangwenjun.concurrent.chapter7_immutable;

import java.util.stream.IntStream;

/**
 * @author malichun
 * @create 2022/11/23 0023 21:16
 */
public class ImmutableClient {
    public static void main(String[] args) {
        // 共享数据
        Person person = new Person("Alex", "GanSu");

        IntStream.rangeClosed(0, 5).forEach(i -> {
            new UserPersonThread(person).start();
        });


    }
}
