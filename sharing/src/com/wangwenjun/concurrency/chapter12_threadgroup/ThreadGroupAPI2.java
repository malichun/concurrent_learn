package com.wangwenjun.concurrency.chapter12_threadgroup;


/**
 * 测试守护线程组
 * @author malichun
 * @create 2022/11/20 0020 15:32
 */
public class ThreadGroupAPI2 {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                //while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //}
            }
        };
        tg1.setDaemon(true); //如果设置成守护的方式, 只有里面线程执行结束后, 线程组会自动destroy
        t1.start();

        Thread.sleep(2000);
        System.out.println(tg1.isDestroyed()); // true

        // 如果不是守护线程组, 则需要手动destroy
        //tg1.destroy();


    }
}
