package com.wangwenjun.concurrent.chapter15_thread_per_message;

/**
 * @author malichun
 * @create 2022/12/05 0005 20:21
 */
public class Message {
    private final String value;

    public Message(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
