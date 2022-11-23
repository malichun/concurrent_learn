package com.wangwenjun.concurrent.chapter4_observerpattern;

/**
 * @author malichun
 * @create 2022/11/22 0022 22:00
 */
public abstract class Observer {
    protected Subject subject;

    public Observer(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();


}
