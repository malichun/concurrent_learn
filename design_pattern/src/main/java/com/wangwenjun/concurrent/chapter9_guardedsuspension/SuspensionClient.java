package com.wangwenjun.concurrent.chapter9_guardedsuspension;

/**
 * 暂停
 * @author malichun
 * @create 2022/11/26 0026 14:52
 */
public class SuspensionClient {
    public static void main(String[] args) throws InterruptedException {
        RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        Thread.sleep(10000);

        serverThread.close();
    }
}
