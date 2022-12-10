package com.wangwenjun.concurrent.classloader.chapter2;

/**
 * @author malichun
 * @create 2022/12/10 0010 16:14
 */
public class BootClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {

        // 根加载器
        System.out.println(System.getProperty("sun.boot.class.path")); //D:\software\JAVA8\jre\lib\resources.jar;D:\software\JAVA8\jre\lib\rt.jar;D:\software\JAVA8\jre\lib\sunrsasign.jar;D:\software\JAVA8\jre\lib\jsse.jar;D:\software\JAVA8\jre\lib\jce.jar;D:\software\JAVA8\jre\lib\charsets.jar;D:\software\JAVA8\jre\lib\jfr.jar;D:\software\JAVA8\jre\classes

        // 扩展类加载器
        System.out.println(System.getProperty("java.ext.dirs")); //D:\software\JAVA8\jre\lib\ext;C:\Windows\Sun\Java\lib\ext

        // 系统类加载器
        System.out.println(System.getProperty("java.class.path"));

        //
        Class<?> klass = Class.forName("com.wangwenjun.concurrent.classloader.chapter2.SimpleObject");
        System.out.println(klass.getClassLoader()); //sun.misc.Launcher$AppClassLoader@14dad5dc
        System.out.println(klass.getClassLoader().getParent()); //sun.misc.Launcher$ExtClassLoader@28d93b30
        System.out.println(klass.getClassLoader().getParent().getParent()); //null 根加载器

        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader()); // null
        System.out.println(clazz);


    }

}
