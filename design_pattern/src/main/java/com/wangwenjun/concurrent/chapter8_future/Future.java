package com.wangwenjun.concurrent.chapter8_future;

import java.util.function.Consumer;

/**
 * @author malichun
 * @create 2022/11/23 0023 21:50
 */
public interface Future<T> {

    T get() throws InterruptedException;

}

// 真正做事情
interface FutureTask<T>{
    T call();
}

// 异步Future实现
class AsyncFuture<T> implements Future<T>{
    private volatile boolean done = false;

    private T result;
    public void done(T result){
        synchronized (this){
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }
    @Override
    public T get() throws InterruptedException {
        synchronized (this){
            while(!done){
               this.wait();
            }
        }
        return result;
    }
}

class FutureService{
    public <T> Future<T> submit(final FutureTask<T> task){
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            asyncFuture.done(result);
        }).start();
        return asyncFuture;
    }

    public <T> Future submit(final FutureTask<T> task, final Consumer<T> consumer){
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            asyncFuture.done(result);
            consumer.accept(result);
        }).start();
        return asyncFuture;
    }
}
