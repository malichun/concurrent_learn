package com.wangwenjun.concurrent.chapter4_observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author malichun
 * @create 2022/11/22 0022 21:57
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();

    // 状态
    private int state;

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        if(state == this.state){
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    // 添加Observer
    public void attach(Observer observer){
        observers.add(observer);
    }

    // 通知到所有的
    private void notifyAllObserver(){
        observers.forEach(Observer::update);
    }
}
