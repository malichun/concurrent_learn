package com.wangwenjun.concurrent.chapter1;

/**
 * 有问题
 * @author malichun
 * @create 2022/11/21 0021 22:41
 */
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3(){}

    // 加锁, 比较慢
    public synchronized static SingletonObject3 getInstance(){
        if(null == instance){
            instance = new SingletonObject3();
        }
        // 让看的比较清楚
        return SingletonObject3.instance;
    }
}
