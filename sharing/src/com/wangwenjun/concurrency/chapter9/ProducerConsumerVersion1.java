package com.wangwenjun.concurrency.chapter9;

/**
 * 生产者消费者
 * @author malc
 * @create 2022/11/19 0019 20:24
 */
public class ProducerConsumerVersion1 {

    private int i = 1;

    final private Object LOCK = new Object();

    private void produce(){
        synchronized (LOCK){
            System.out.println("P->"+ (i++));
        }
    }

    private void consume(){
        synchronized (LOCK){
            System.out.println("C->" +(i));
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion1 pc = new ProducerConsumerVersion1();
        new Thread("P"){
            @Override
            public void run() {
                while(true) {
                    pc.produce();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while(true) {
                    pc.consume();
                }
            }
        }.start();

    }

}
