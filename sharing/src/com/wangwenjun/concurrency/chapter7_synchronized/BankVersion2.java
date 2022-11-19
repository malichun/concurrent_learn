package com.wangwenjun.concurrency.chapter7_synchronized;

/**
 * @author malc
 * @create 2022/11/19 0019 17:46
 */
public class BankVersion2 {
    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(ticketWindow, "一号窗口");
        Thread windowThread2 = new Thread(ticketWindow, "二号窗口");
        Thread windowThread3 = new Thread(ticketWindow, "三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();

    }
}
