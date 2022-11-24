package com.wangwenjun.concurrent.chapter7_immutable;

/**
 * @author malichun
 * @create 2022/11/23 0023 21:31
 */
public class ImmutablePerformanceTest {
    public static void main(String[] args) {
        long startTimestamp = System.currentTimeMillis();
        //SyncObj syncObj = new SyncObj();
        //syncObj.setName("Alex");
        //for(long l=0L;l<10000000;l++){
        //    syncObj.toString();
        //}

        ImmutableObj immutableObj = new ImmutableObj("Alex");
        for(int i=0;i<10000000;i++){
            immutableObj.toString();
        }
        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time "+(endTimestamp-startTimestamp));
    }
}

final class ImmutableObj{
    private final String name;
    ImmutableObj(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name +"]";
    }
}

class SyncObj{
    private String name;
    public synchronized void setName(String name){
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "[" + name +"]";
    }
}
