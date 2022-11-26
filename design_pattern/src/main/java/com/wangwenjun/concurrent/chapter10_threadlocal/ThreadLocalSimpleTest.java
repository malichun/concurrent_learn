package com.wangwenjun.concurrent.chapter10_threadlocal;

/**
 * @author malichun
 * @create 2022/11/26 0026 15:54
 */
public class ThreadLocalSimpleTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){ //
        // 默认值
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };



    public static void main(String[] args) throws InterruptedException {
        //threadLocal.set("Alex");

        System.out.println(threadLocal.get()); // 拿的默认值

        Thread.sleep(1000);

    }
}
