package com.wangwenjun.concurrency.chapter7_synchronized;

/**
 * @author malc
 * @create 2022/11/19 0019 18:43
 */
public class SynchronizedRunnable implements Runnable {
    private int index = 1;

    // readonly 共享数据
    private final static int MAX = 500;

    private final Object MONITOR = new Object();

    // 锁对象为 this
    @Override
    public void run() { // 同步方法
        while (true) {
            if(ticket()){
                break;
            }

        }
    }

    private synchronized boolean ticket(){
        if(index > MAX) {
            return true;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //1.get Field index
        //2.index = index+1
        //3.put field 到局部变量表
        System.out.println(Thread.currentThread() + " 的号码是:" + (index++));
        return false;
    }
}
