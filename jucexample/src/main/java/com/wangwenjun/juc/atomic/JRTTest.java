package com.wangwenjun.juc.atomic;

/**
 * jrt即时编译器
 */
public class JRTTest {
    private  static boolean init = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                while(!init){
                    System.out.println("xxx");
                }
                /**
                 * jrt在运行时优化了
                 * while(true){}
                 * 如果里面有一句话
                 * while(!init){
                 *     System.out.println("xxx");
                 * }
                 */
            }
        }.start();

        Thread.sleep(1000);

        new Thread(() -> {
            init = true;
            System.out.println("Set init to true");
        }).start();

    }

}
