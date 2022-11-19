package com.wangwenjun.concurrency.chapter2;

/**
 * @author malichun
 * @create 2022/11/16 0016 21:59
 */
public class BankVersion2 {
    private final static int MAX = 5000;
    public static void main(String[] args) {
        //
        //final TicketWidowRunnable ticketWindow = new TicketWidowRunnable();
        final Runnable ticketWindow = () -> {
            int index = 1;

            while(index <= MAX){
                System.out.println(Thread.currentThread() + " 的号码是:" + (index++));
                //try {
                //    Thread.sleep(100);
                //} catch (InterruptedException e) {
                //    e.printStackTrace();
                //}
            }
        };
        Thread windowThread1 = new Thread(ticketWindow, "一号窗口");
        Thread windowThread2 = new Thread(ticketWindow, "二号窗口");
        Thread windowThread3 = new Thread(ticketWindow, "三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
