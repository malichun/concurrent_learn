package com.wangwenjun.concurrent.chapter16_two_phase_terminate;

import java.util.Random;

/**
 * @author malichun
 * @create 2022/12/05 0005 20:47
 */
public class CounterIncrement extends Thread{
    private volatile boolean terminated = false;
    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try{
            while(!terminated){
                System.out.println(Thread.currentThread().getName()+" "+counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean(){
        // 第二个阶段
        System.out.println("做一些清理操作....."+counter);
    }

    public void close(){
        terminated = true;
    }
}
