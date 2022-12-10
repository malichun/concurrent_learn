package com.wangwenjun.concurrent.classloader.chapter3;

/**
 * 1.类加载器的委托是优先交给父亲加载器先取尝试加载
 * 2.父类加载器和子加载器其实是一种包装关系, 或者包含关系
 *
 */
public class MyClassLoaderTest2 {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader1 = new MyClassLoader("MyClassLoader-1");
        MyClassLoader classLoader2 = new MyClassLoader("MyClassLoader-22", classLoader1);
        classLoader2.setDir("D:/projects/concurrent_learn/app/classloader2/"); // 没有目录

        Class<?> aClass = classLoader2.loadClass("com.wangwenjun.concurrent.classloader.chapter3.MyObject");

        System.out.println(aClass.getClass());
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());

    }

}
