package com.wangwenjun.concurrent.chapter1_singleton;

/**
 * 有问题
 * @author malichun
 * @create 2022/11/21 0021 22:41
 */
public class SingletonObject2 {
    private static SingletonObject2 instance;

    private SingletonObject2(){}

    public static SingletonObject2 getInstance(){
        if(null == instance){
            instance = new SingletonObject2();
        }
        // 让看的比较清楚
        return SingletonObject2.instance;
    }
}
