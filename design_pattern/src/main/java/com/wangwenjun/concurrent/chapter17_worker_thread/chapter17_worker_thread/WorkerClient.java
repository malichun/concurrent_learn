package com.wangwenjun.concurrent.chapter17_worker_thread.chapter17_worker_thread;

/**
 * @author malichun
 * @create 2022/12/06 0006 0:09
 */
public class WorkerClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        // 搬货
        new TransportThread("Alex", channel).start();
        new TransportThread("Jack", channel).start();
        new TransportThread("William", channel).start();

    }
}
