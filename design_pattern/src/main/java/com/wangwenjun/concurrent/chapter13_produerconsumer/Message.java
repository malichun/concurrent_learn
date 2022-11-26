package com.wangwenjun.concurrent.chapter13_produerconsumer;

/**
 * @author malichun
 * @create 2022/11/26 0026 20:25
 */
public class Message {
    private String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Message{" +
            "data='" + data + '\'' +
            '}';
    }
}
