package com.wangwenjun.concurrent.chapter3_volatile;

/**
 *
 * 不加volatile, 会导致一个只有写的线程一直读的自己的缓存,不会更新到主存
 * @author malichun
 * @create 2022/11/22 0022 12:45
 */
public class VolatileTest {

    private  static  int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 500;

    public static void main(String[] args) {

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while(localValue < MAX_LIMIT){
                if(localValue!=INIT_VALUE){ // 不加volatile, 会一直不相等, 没有写的操作, 读取操作, 不需要取更新主内存
                    System.out.printf("值被更新为 %d\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread(() ->{
            int localValue = INIT_VALUE;
            while(INIT_VALUE < MAX_LIMIT){
                System.out.printf("更新值为 %d\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();


    }
}
