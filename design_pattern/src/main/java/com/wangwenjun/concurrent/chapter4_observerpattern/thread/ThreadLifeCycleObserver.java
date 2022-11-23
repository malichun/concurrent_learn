package com.wangwenjun.concurrent.chapter4_observerpattern.thread;

import java.util.List;

/**
 * 线程声明周期
 *
 * @author malichun
 * @create 2022/11/22 0022 22:27
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    // 根据线程id查询
    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.forEach(id -> {
            new Thread(new ObserverRunnable(this) {
                @Override
                public void run() {
                    try {
                        notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                        System.out.println("query for the id " + id);
                        Thread.sleep(1000L);
                        notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                    } catch (Exception e) {
                        notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                    }
                }
            }, id).start();
        });
    }

    // 回调
    @Override
    public void onEvent(ObserverRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The runnable ["+event.getThread().getName()+"] data changed and state is ["+ event.getState()+"]");
            if(event.getCause() != null){
                //失败了
                System.out.println("The runnable ["+event.getThread().getName()+"] process failed");
                event.getCause().printStackTrace();
            }
        }
    }
}
