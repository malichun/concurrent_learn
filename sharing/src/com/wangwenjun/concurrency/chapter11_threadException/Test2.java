package com.wangwenjun.concurrency.chapter11_threadException;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author malichun
 * @create 2022/11/20 0020 14:43
 */
public class Test2 {
    public void test(){
        Arrays.stream(Thread.currentThread().getStackTrace())
            .filter(e -> !e.isNativeMethod())
            .forEach(e -> Optional.of(e.getClassName()+":"+e.getMethodName()+":"+e.getLineNumber())
                .ifPresent(System.out::println)
            );
    }
}
