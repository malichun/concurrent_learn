package com.wangwenjun.concurrent.chapter6_readwritelock;

import java.util.Random;

/**
 * @author malichun
 * @create 2022/11/23 0023 19:36
 */
public class WriterWorker extends Thread{

    private static final Random random = new Random(System.currentTimeMillis());

    private final SharedData data;

    private final String filler;

    private int index = 0;

    public WriterWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private char nextChar(){
        char c = filler.charAt(index);
        index++;
        if(index>=filler.length()){
            index=0;
        }
        return c;
    }
}
