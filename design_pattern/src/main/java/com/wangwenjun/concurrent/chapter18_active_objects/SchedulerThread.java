package com.wangwenjun.concurrent.chapter18_active_objects;

/**
 * @author malichun
 * @create 2022/12/07 0007 20:59
 */
public class SchedulerThread extends Thread{

    private final ActivationQueue activationQueue;


    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request){
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while(true){
            activationQueue.take().execute();
        }
    }
}
