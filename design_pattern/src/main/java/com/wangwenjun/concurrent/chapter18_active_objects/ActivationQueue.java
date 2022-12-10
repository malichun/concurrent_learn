package com.wangwenjun.concurrent.chapter18_active_objects;

import java.util.LinkedList;

/**
 * @author malichun
 * @create 2022/12/07 0007 20:45
 */
public class ActivationQueue {
    private final static int MAX_METHOD_REQUEST_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        this.methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request){
        while(methodQueue.size()>=MAX_METHOD_REQUEST_SIZE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(request);
        this.notifyAll();
    }

    public synchronized  MethodRequest take(){
        while(methodQueue.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
}
