package com.wangwenjun.concurrent.chapter1;

/**
 * DoubleCheckedLock DCL
 * 还有问题, 有可能会空指针异常
 * @author malichun
 * @create 2022/11/21 0021 22:41
 */
public class SingletonObject4 {
    private static SingletonObject4 instance;

    private Object obj;
    private SingletonObject4(){ // 并没有完成
        int i =0;
        int j =10;

        //obj;

    }

    // double check, 可能会引起空指针异常, 重排序
    public static SingletonObject4 getInstance(){
        if(null == instance){
            synchronized (SingletonObject4.class){
                if(null == instance){
                    instance = new SingletonObject4();
                }
            }
        }
        // 让看的比较清楚
        return SingletonObject4.instance;
    }
}
