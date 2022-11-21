package com.wangwenjun.concurrent.chapter1;

/**
 * 1.饿汉式单例
 * @author malichun
 * @create 2022/11/21 0021 22:36
 */
public class SingletonObject1 {

    // static 修饰是ClassLoader加载, 不能懒加载
    private static final SingletonObject1 instance  = new SingletonObject1();

    // 私有构造
    private SingletonObject1(){}

    public static SingletonObject1 getInstance(){
        return instance;
    }
}
