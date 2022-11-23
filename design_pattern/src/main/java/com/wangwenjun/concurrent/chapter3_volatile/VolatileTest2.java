package com.wangwenjun.concurrent.chapter3_volatile;

/**
 * 两边都有写的操作, 会同步到主内存
 * 还是更新到主内存了
 * @author malichun
 * @create 2022/11/22 0022 13:39
 */
public class VolatileTest2 {
    private  static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 50;

    public static void main(String[] args) {

        new Thread(() -> {
            while(INIT_VALUE < MAX_LIMIT){
                System.out.println("T1->"+(++INIT_VALUE));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() ->{
            while(INIT_VALUE < MAX_LIMIT){
                System.out.println("T2->"+(++INIT_VALUE));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-2").start();

    }

}
