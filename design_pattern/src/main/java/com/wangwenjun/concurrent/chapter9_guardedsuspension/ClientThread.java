package com.wangwenjun.concurrent.chapter9_guardedsuspension;

import java.util.Random;

/**
 * @author malichun
 * @create 2022/11/26 0026 13:54
 */
public class ClientThread extends Thread{

    private final RequestQueue queue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue queue, String sendValue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
        this.sendValue = sendValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Client -> request " + sendValue);
            queue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
