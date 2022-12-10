package com.wangwenjun.concurrent.classloader.chapter4;

import java.lang.reflect.Method;

/**
 * @author malichun
 * @create 2022/12/10 0010 18:51
 */
public class DecryptClassLoaderTest {
    public static void main(String[] args) throws Exception {
        DecryptClassLoader decryptClassLoader = new DecryptClassLoader();
        Class<?> aClass = decryptClassLoader.loadClass("com.wangwenjun.concurrent.classloader.chapter3.MyObject");
        System.out.println(aClass); // com.wangwenjun.concurrent.classloader.chapter3.MyObject
        System.out.println(aClass.getClassLoader()); // com.wangwenjun.concurrent.classloader.chapter3.MyClassLoader@1b6d3586

        Object obj = aClass.newInstance();
        Method method = aClass.getMethod("hello");

        Object result = method.invoke(obj);
        System.out.println(result);
    }
}
