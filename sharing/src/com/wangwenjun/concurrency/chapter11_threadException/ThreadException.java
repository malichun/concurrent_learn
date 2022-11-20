package com.wangwenjun.concurrency.chapter11_threadException;

/**
 * 捕获线程异常
 *
 * Runnable 的run方法没有抛出异常, 子类就不好抛出
 * @author malichun
 * @create 2022/11/20 0020 14:33
 */
public class ThreadException {
    private final static int A = 10;
    private final static int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //// 外面捕获异常
        //t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
        //    @Override
        //    public void uncaughtException(Thread t, Throwable e) {
        //        System.out.println(e);
        //        System.out.println(t);
        //    }
        //});

        t.start();
    }

}
// java.lang.ArithmeticException: / by zero
//Thread[Thread-0,5,main]