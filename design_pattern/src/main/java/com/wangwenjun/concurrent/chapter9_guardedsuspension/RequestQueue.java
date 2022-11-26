package com.wangwenjun.concurrent.chapter9_guardedsuspension;

import java.util.LinkedList;

/**
 * 请求队列
 */
public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait(); // 没有就等一下
                } catch (InterruptedException e) {
                    // 被打断了, 说明有
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
