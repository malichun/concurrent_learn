package com.wangwenjun.concurrent.chapter16_two_phase_terminate;

/**
 * @author malichun
 * @create 2022/12/05 0005 20:51
 */
public class CounterTest {
    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        Thread.sleep(10000);

        counterIncrement.close();
    }
}
