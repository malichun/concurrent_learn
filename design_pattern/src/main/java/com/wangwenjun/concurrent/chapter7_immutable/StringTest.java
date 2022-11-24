package com.wangwenjun.concurrent.chapter7_immutable;

/**
 * @author malichun
 * @create 2022/11/23 0023 21:21
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "Hello";
        String s2 = s.replace("l", "k");
        System.out.println(s2.getClass()+" "+s2.hashCode());
        System.out.println(s.getClass() +" "+s.hashCode());
    }
}
