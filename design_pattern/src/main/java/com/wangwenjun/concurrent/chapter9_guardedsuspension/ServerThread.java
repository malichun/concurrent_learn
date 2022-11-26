package com.wangwenjun.concurrent.chapter9_guardedsuspension;

import java.util.Random;

/**
 * @author malichun
 * @create 2022/11/26 0026 14:30
 */
public class ServerThread extends Thread{

    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = true;

    public ServerThread(RequestQueue queue){
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while(flag){
            Request request = queue.getRequest(); // 拿不到数据会wait住, 如果被打断
            if(null == request){
                System.out.println("Received the empty request.");
                continue; // 忽略一个打断
            }
            System.out.println("Server -> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close(){
        this.flag = false;
        this.interrupt(); // 会打断当前线程的wait方法
    }
}
