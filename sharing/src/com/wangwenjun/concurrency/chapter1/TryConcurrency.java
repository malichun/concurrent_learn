package com.wangwenjun.concurrency.chapter1;

/**
 * @author malichun
 * @create 2022/11/14 0014 20:50
 */
public class TryConcurrency {
    public static void main(String[] args) {
        new Thread("READ-Thread") {
            @Override
            public void run() {
                println(Thread.currentThread());
                readFromDataBase();
            }
        }.start();

        new Thread("WRITE-Thread"){
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();

        try {
            Thread.sleep(30000 * 10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void readFromDataBase(){
        try {
            println("Begin read data from db.");
            Thread.sleep(30*1000L);
            println("read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void writeDataToFile(){
        try {
            println("Begin write data to file.");
            Thread.sleep(30*1000L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void println(Object message){
        System.out.println(message);
    }
}
