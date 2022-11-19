package com.wangwenjun.concurrency.chapter2;

/**
 * @author malichun
 * @create 2022/11/16 0016 21:57
 */
public class TicketWidowRunnable implements Runnable{
    private int index =0;
    private final static int MAX = 50;

    @Override
    public void run() {
        while(index <= MAX){
            System.out.println(Thread.currentThread() + " 的号码是:" + (index++));
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
