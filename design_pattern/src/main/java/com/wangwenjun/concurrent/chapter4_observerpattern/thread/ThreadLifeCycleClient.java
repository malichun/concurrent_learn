package com.wangwenjun.concurrent.chapter4_observerpattern.thread;

import java.util.Arrays;

/**
 * @author malichun
 * @create 2022/11/22 0022 22:39
 */
public class ThreadLifeCycleClient {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }
}
