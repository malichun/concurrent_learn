package com.wangwenjun.concurrent.chapter18_active_objects;

/**
 * @author malichun
 * @create 2022/12/07 0007 21:10
 */
public final class ActiveObjectFactory {
    private ActiveObjectFactory() {}

    public static ActiveObject createActiveObject(){
        Servant servant = new Servant();
        ActivationQueue queue  =new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(queue);
        ActiveObjectProxy proxy = new ActiveObjectProxy(schedulerThread, servant);
        schedulerThread.start();
        return proxy;
    }
}
