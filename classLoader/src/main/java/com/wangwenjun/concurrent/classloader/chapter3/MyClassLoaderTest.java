package com.wangwenjun.concurrent.classloader.chapter3;

import java.lang.reflect.Method;

/**
 * @author malichun
 * @create 2022/12/10 0010 17:37
 */
public class MyClassLoaderTest {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("MyClassLoader");
        Class<?> aClass = classLoader.loadClass("com.wangwenjun.concurrent.classloader.chapter3.MyObject");
        System.out.println(aClass); // com.wangwenjun.concurrent.classloader.chapter3.MyObject
        System.out.println(aClass.getClassLoader()); // com.wangwenjun.concurrent.classloader.chapter3.MyClassLoader@1b6d3586

        Object obj = aClass.newInstance();
        Method method = aClass.getMethod("hello");

        Object result = method.invoke(obj);
        System.out.println(result);

    }
}
