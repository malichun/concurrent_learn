package com.wangwenjun.concurrent.classloader.chapter5;

/**
 * @author malichun
 * @create 2022/12/10 0010 18:59
 */
public class SimpleClassLoaderTest {

    public static void main(String[] args) throws Exception {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.wangwenjun.concurrent.classloader.chapter5.SimpleObject");
        System.out.println(aClass.getClassLoader()); // com.wangwenjun.concurrent.classloader.chapter5.SimpleClassLoader@1b6d3586

    }
}
