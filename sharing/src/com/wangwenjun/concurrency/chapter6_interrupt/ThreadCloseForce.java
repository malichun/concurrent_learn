package com.wangwenjun.concurrency.chapter6_interrupt;

/**
 * 在while里面, 一步很长时间, 需要强制关闭
 * @author malc
 * @create 2022/11/19 0019 15:52
 */
public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(() -> {
            //加载很大的文件...
            //while(true){}

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10000); // 10s钟结束
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
