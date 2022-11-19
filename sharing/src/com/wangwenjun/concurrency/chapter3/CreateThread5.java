package com.wangwenjun.concurrency.chapter3;

/**
 * @author malichun
 * @create 2022/11/18 0018 23:00
 */
public class CreateThread5 {

    private static int counter = 1;

    public static void main(String[] args) {

        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                counter++;
                new Thread(() -> {
                    byte[] data = new byte[1024 * 1024 * 2];
                    while (true) {
                        /* try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } */
                    }
                }).start();
            }
            counter++;

        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println("总的线程数=> " + counter);
    }
}
