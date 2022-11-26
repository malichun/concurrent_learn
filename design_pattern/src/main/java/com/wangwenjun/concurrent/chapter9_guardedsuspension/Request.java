package com.wangwenjun.concurrent.chapter9_guardedsuspension;

public class Request {
    final private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
