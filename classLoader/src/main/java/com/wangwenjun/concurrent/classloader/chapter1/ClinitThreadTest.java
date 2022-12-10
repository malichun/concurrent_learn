package com.wangwenjun.concurrent.classloader.chapter1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * @author malichun
 * @create 2022/12/10 0010 16:02
 */
public class ClinitThreadTest {
    public static void main(String[] args) {
        new Thread(() -> new SimpleObject()).start();
        new Thread(() -> new SimpleObject()).start();
    }

    static class SimpleObject{
        // 只会打印一次
        private static AtomicBoolean init = new AtomicBoolean(true);
        static {
            System.out.println(Thread.currentThread().getName());

            System.out.println("I will be initial");
            //while(init.get()){
            //
            //}
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finished initial");
        }
    }
}
