package com.wangwenjun.concurrency.chapter3;

/**
 * @author malichun
 * @create 2022/11/18 0018 22:34
 */
public class CreateThread4 {

    private static int counter = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                }catch (Error e){
                    System.out.println(counter);
                }
            }

            private void add(int i){
                counter++;
                add(i+1);
            }
        }, "Test", 1 << 24); // stackSize 8M的内存, 可以使递归变的更深

        t1.start();

    }
}
