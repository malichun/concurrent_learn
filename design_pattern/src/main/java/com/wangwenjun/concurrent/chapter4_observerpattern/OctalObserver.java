package com.wangwenjun.concurrent.chapter4_observerpattern;

/**
 * @author malichun
 * @create 2022/11/22 0022 22:06
 */
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String:"+Integer.toOctalString(subject.getState()));
    }
}
