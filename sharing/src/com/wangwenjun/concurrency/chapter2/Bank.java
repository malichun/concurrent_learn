package com.wangwenjun.concurrency.chapter2;

/**
 * @author malichun
 * @create 2022/11/16 0016 21:39
 */
public class Bank {
    public static void main(String[] args) {

        TicketWindow ticketWindow1 = new TicketWindow("1号柜台");
        TicketWindow ticketWindow2 = new TicketWindow("2号柜台");
        TicketWindow ticketWindow3 = new TicketWindow("3号柜台");

        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();

    }
}
