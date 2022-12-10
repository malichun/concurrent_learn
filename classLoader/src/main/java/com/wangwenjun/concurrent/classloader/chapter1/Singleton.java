package com.wangwenjun.concurrent.classloader.chapter1;

/**
 * @author malichun
 * @create 2022/12/10 0010 12:30
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    public static int x = 0;

    public static int y ;

    private Singleton(){
        x++;
        y++;
    }


    // 调用静态方法会初始化类
    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x); //
        System.out.println(singleton.y); //1
    }

}
