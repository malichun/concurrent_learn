package com.wangwenjun.concurrent.classloader.chapter6;

import com.wangwenjun.concurrent.classloader.chapter3.MyClassLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 线程上下文类加载器
 */
public class ThreadContextClassLoader {
    public static void main(String[] args) {

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        System.out.println(contextClassLoader); // 是系统加载器(第三层的类加载器),sun.misc.Launcher$AppClassLoader@14dad5dc

        // 切换为自定义的类加载器
        Thread.currentThread().setContextClassLoader(new MyClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader()); // 是系统加载器(第三层的类加载器),com.wangwenjun.concurrent.classloader.chapter3.MyClassLoader@1b6d3586

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
