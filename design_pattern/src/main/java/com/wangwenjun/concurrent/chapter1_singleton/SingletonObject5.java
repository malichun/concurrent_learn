package com.wangwenjun.concurrent.chapter1_singleton;

/**
 * DoubleCheckedLock DCL
 *
 * @author malichun
 * @create 2022/11/21 0021 22:41
 */
public class SingletonObject5 {
    private static volatile SingletonObject5 instance;

    private SingletonObject5(){
    }

    // double check add volatile
    public static SingletonObject5 getInstance(){
        if(null == instance){
            synchronized (SingletonObject5.class){
                if(null == instance){
                    instance = new SingletonObject5();
                }
            }
        }
        // 让看的比较清楚
        return SingletonObject5.instance;
    }
}
