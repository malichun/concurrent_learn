package com.wangwenjun.concurrent.chapter1;

/**
 * 采用匿名内部类
 * @author malichun
 * @create 2022/11/21 0021 23:16
 */
public class SingletonObject6 {

    private SingletonObject6(){}

    private static class InstanceHolder{
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
    }
}
