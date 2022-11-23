package com.wangwenjun.concurrent.chapter4_observerpattern.thread;

/**
 * @author malichun
 * @create 2022/11/22 0022 22:23
 */
public interface LifeCycleListener {

    void onEvent(ObserverRunnable.RunnableEvent event);
}
