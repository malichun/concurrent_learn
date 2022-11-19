package com.wangwenjun.concurrency.chapter5_join;

/**
 * @author malc
 * @create 2022/11/19 0019 14:18
 */
public class ThreadJoin3 {
    // 采集各个服务器节点的数据
    public static void main(String[] args) throws InterruptedException {
        long startTimestamp = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("M1", 10_000L));
        Thread t2 = new Thread(new CaptureRunnable("M2", 30_000L));
        Thread t3 = new Thread(new CaptureRunnable("M3", 15_000L));


        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTimestamp = System.currentTimeMillis();
        System.out.printf("保存数据开始时间: %s, 结束时间: %s\n", startTimestamp, endTimestamp);

        //结果:
        //M1 完成数据采集..., 时间戳为: 1668840169174
        //M3 完成数据采集..., 时间戳为: 1668840174169
        //M2 完成数据采集..., 时间戳为: 1668840189170
        //保存数据开始时间: 1668840159167, 结束时间: 1668840189170
    }
}

class CaptureRunnable implements Runnable{

    private String machineName;

    private long spendTime;

    public CaptureRunnable(String machineName, long spendTime){
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        // 实施真正的采集数据
        try {
            Thread.sleep(spendTime);
            System.out.println(machineName+" 完成数据采集..., 时间戳为: "+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getResult(){
        return machineName+" finish.";
    }
}
