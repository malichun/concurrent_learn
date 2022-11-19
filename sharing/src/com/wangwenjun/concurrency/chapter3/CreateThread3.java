package com.wangwenjun.concurrency.chapter3;

/**
 * 测试虚拟机栈溢出, ThreadGroup使用到
 * @author malichun
 * @create 2022/11/18 0018 21:58
 */
public class CreateThread3 {
    private int i = 0;

    private byte[] bytes = new byte[1024];

    private static int counter = 0;

    // JVM will create a thread named main
    public static void main(String[] args) {
        // 创建了一个虚拟机栈

        int j = 0; // 局部变量表
        int[] arr = new int[1024]; // 局部变量表存放的地址, 数据放到堆里面了

        try {
            add(0);
        }catch (Error e){
            e.printStackTrace();
            System.out.println(counter);
        }
    }

    private static void add(int i){
        ++counter;
        add(i+1);
    }
}

// StackOverflowError
// 22576
