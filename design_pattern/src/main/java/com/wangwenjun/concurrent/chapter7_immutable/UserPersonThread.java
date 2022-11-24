package com.wangwenjun.concurrent.chapter7_immutable;

/**
 * @author malichun
 * @create 2022/11/23 0023 21:15
 */
public class UserPersonThread extends Thread{

    private Person person;

    public UserPersonThread(Person person){
        this.person = person;
    }
    @Override
    public void run() {
        while(true){
            System.out.println(Thread.currentThread().getName()+" print "+person.toString());
        }
    }
}
