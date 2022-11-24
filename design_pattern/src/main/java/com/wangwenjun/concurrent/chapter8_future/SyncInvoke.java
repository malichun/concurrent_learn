package com.wangwenjun.concurrent.chapter8_future;

/**
 * Future -> 代表的是未来的一个凭据
 * FutureTask -> 将你的调用逻辑进行了隔离
 * FutureService -> 桥接Future 和 FutureTask
 */
public class SyncInvoke {
    public static void main(String[] args) throws InterruptedException {
        //String result = get();
        //System.out.println(result);

        FutureService futureService = new FutureService();
        futureService.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        },System.out::println);

        System.out.println("==============");
        System.out.println("do other thing");
        Thread.sleep(1000);
        System.out.println("==============");


    }

    private static String get() throws InterruptedException {
        Thread.sleep(10000);
        return "FINISH";
    }
}
