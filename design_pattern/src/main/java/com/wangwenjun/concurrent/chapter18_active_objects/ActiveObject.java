package com.wangwenjun.concurrent.chapter18_active_objects;

/**
 * 接受异步消息的主动对象
 */
public interface ActiveObject {

    Result makeString(int count, char fillChar);

    void displayString(String text);
}
