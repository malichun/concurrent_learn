package com.wangwenjun.concurrency.chapter6;

/**
 * @author malc
 * @create 2022/11/19 0019 15:56
 */
public class ThreadService {

    // 执行线程
    private Thread executeThread;

    // 标记位, task结束,主任务结束
    private boolean finished = false;

    // 使用守护线程
    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run() {
                //创建一个守护线程!!!!!!!!!!!
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();

                try {
                    runner.join(); // 执行线程blocked了(外面的线程)!!!!, 线程会收到interrupt影响
                    finished = true;
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }
        };

        executeThread.start();
    }

    // 等待毫秒数, 超过这个时间就被干掉
    public void shutdown(long mills){
        long currentTime = System.currentTimeMillis();
        while(!finished){
            // 超时了
            if((System.currentTimeMillis() - currentTime) >= mills){
                System.out.println("任务超时, 需要结束");
                executeThread.interrupt(); // 会导致执行线程结束, 从而导致其下面的守护线程结束
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断!");
                break;
            }
        }
        finished = false;
    }
}
