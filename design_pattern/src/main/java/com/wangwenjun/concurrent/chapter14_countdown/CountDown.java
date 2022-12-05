package com.wangwenjun.concurrent.chapter14_countdown;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 自定义倒计时锁
 *
 */
public class CountDown {
    private final int total;

    private int counter = 0;

    public CountDown(int total){
        this.total = total;
    }

    public void countDown(){
        synchronized (this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this){
            while(counter!=total){
                this.wait();
            }
        }
    }
}

class Test{
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(System.currentTimeMillis());
        // 倒计时锁
        CountDown countdown = new CountDown(5);

        System.out.println("准备多线程处理任务.");
        // 第一阶段
        IntStream.rangeClosed(1,5)
            .forEach(i -> {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName()+" 开始工作....");
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" 工作完成了...");
                    countdown.countDown();
                }, String.valueOf(i)).start();
            });

        countdown.await();
        // 第二阶段
        System.out.println("多线程处理任务全部结束, 准备第二阶段任务");
        System.out.println("..................");
        System.out.println("FINISH");
    }
}
