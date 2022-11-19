package com.wangwenjun.concurrency.chapter5_join;

/**
 * @author malc
 * @create 2022/11/19 0019 14:11
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        /* Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 开始执行");
                Thread.sleep(10_000);
                System.out.println("t1 开始done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        t1.join(100, 10);

        Optional.of("所有的task结束了.").ifPresent(System.out::println);
 */
        // start httpServer
        // JettyHttpServer.start()

        Thread.currentThread().join(); // 会一直是运行状态, main线程在等自己结束

    }
}
