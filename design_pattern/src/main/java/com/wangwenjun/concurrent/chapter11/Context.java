package com.wangwenjun.concurrent.chapter11;

/**
 * 上下文
 *
 */
public class Context {
    private String name;
    private String cardId;
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
