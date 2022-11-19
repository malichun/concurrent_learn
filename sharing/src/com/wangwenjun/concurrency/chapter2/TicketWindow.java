package com.wangwenjun.concurrency.chapter2;

/**
 * @author malichun
 * @create 2022/11/16 0016 21:37
 */
public class TicketWindow extends Thread {

    // 柜台
    private final String name ;
    private static final int MAX = 50;

    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while(index<= MAX){
            System.out.println("柜台: " + name + "当前的号码是: "+index++);
        }

    }
}
