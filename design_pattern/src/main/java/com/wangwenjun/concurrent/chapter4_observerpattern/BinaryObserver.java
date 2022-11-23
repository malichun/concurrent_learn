package com.wangwenjun.concurrent.chapter4_observerpattern;

/**
 * @author malichun
 * @create 2022/11/22 0022 22:04
 */
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String:" + Integer.toBinaryString(subject.getState()));
    }
}
