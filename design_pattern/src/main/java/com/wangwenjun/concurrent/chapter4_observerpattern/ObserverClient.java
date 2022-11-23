package com.wangwenjun.concurrent.chapter4_observerpattern;

/**
 * @author malichun
 * @create 2022/11/22 0022 22:10
 */
public class ObserverClient {
    public static void main(String[] args) {
       final Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);

        System.out.println("=========================");
        subject.setState(10);
        System.out.println("=========================");
        subject.setState(10);
        System.out.println("=========================");
        subject.setState(15);

    }
}
