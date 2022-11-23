package com.wangwenjun.concurrent.chapter1_singleton;

/**
 * 使用枚举
 * @author malichun
 * @create 2022/11/21 0021 23:25
 */
public class SingletonObject7 {
    private SingletonObject7(){}

    private enum Singleton{
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton(){ // 只会被装载一次
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance(){
            return instance;
        }
    }

    public static SingletonObject7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
}
