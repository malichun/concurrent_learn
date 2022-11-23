package com.wangwenjun.concurrent.chapter5_gate;

/**
 * 大门
 *
 * 共享自愿
 * @author malichun
 * @create 2022/11/22 0022 22:59
 */
public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /**
     * 临界值, 加一个this锁
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address){
        this.counter++;
        this.name = name;
        this.address = address;
        verify(); // 检查有没有问题
    }

    private void verify(){
        if(this.name.charAt(0)!= this.address.charAt(0)){
            System.out.println("*******BROKEN*******"+toString());
        }
    }

    public synchronized String toString(){
        return "No."+counter+":"+name+":"+address;
    }

}
