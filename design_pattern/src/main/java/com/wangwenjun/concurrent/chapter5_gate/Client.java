package com.wangwenjun.concurrent.chapter5_gate;

/**
 * @author malichun
 * @create 2022/11/22 0022 23:07
 */
public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("Baobao","Beijing",gate);
        User sh = new User("ShangLao","ShangHai",gate);
        User gz = new User("GuangLao","GuangZhou",gate);

        bj.start();
        sh.start();
        gz.start();

    }
}
