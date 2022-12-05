package com.wangwenjun.concurrent.chapter14_countdown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 倒计时锁
 */
public class JDKCountDownLatch {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        // 倒计时锁
        CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println("准备多线程处理任务.");
        // 第一阶段
        IntStream.rangeClosed(1,5)
            .forEach(i -> {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName()+" 开始工作....");
                    try {
                        Thread.sleep(RANDOM.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" 工作完成了...");
                    countDownLatch.countDown();
                }, String.valueOf(i)).start();
            });

        countDownLatch.await();
        // 第二阶段
        System.out.println("多线程处理任务全部结束, 准备第二阶段任务");
        System.out.println("..................");
        System.out.println("FINISH");
    }
}
