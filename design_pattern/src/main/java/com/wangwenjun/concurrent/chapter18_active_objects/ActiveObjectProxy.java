package com.wangwenjun.concurrent.chapter18_active_objects;

/**
 * @author malichun
 * @create 2022/12/07 0007 21:05
 */
class ActiveObjectProxy implements ActiveObject{

    private final SchedulerThread schedulerThread;

    private final Servant servant;

    public ActiveObjectProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fillChar) {
        FutureResult future = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servant,future,count, fillChar));
        return future;
    }

    @Override
    public void displayString(String text) {
        schedulerThread.invoke(new DisplayStringRequest(servant, text));
    }
}
