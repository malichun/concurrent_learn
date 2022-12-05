package com.wangwenjun.concurrent.chapter17_worker_thread.chapter17_worker_thread;

/**
 * 商品
 */
public class Request {

    private final String name;

    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName()+" execute "+this);
    }

    @Override
    public String toString() {
        return "Request=> No. "+number+" Name. "+name;
    }
}
