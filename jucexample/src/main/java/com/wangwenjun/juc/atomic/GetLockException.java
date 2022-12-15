package com.wangwenjun.juc.atomic;

/**
 * @author malichun
 * @create 2022/12/11 0011 17:06
 */
public class GetLockException extends Exception{
    public GetLockException(){
        super();
    }

    public GetLockException(String message){
        super(message);
    }
}
