package com.wangwenjun.concurrent.chapter4_observerpattern.thread;


/**
 * @author malichun
 * @create 2022/11/22 0022 22:20
 */
public abstract class ObserverRunnable implements Runnable{
    protected LifeCycleListener listener;

    public ObserverRunnable(final LifeCycleListener listener){
        this.listener = listener;
    }

    // 通知
    protected void notifyChange(final RunnableEvent event){
        listener.onEvent(event);
    }

    public enum RunnableState{
        RUNNING, ERROR,DONE
    }
    public static class RunnableEvent{
        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
