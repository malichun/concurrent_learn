package com.wangwenjun.concurrency.chapter13_threadpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author malichun
 * @create 2022/11/20 0020 16:26
 */
public class SimpleThreadPool extends Thread { // 让线程池继承Thread
    // 线程个数
    private int size;

    private final int queueSize;

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private static volatile int seq = 0;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    private final DiscardPolicy discardPolicy;

    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("取消任务....");
    };

    private volatile boolean destroy = false;

    private int min; // 最小线程数2
    private int max; // 最大线程数
    private int active; //

    public SimpleThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < this.min; i++) {
            createWorkerTask();
        }
        this.size = min;
        this.start(); // 自己启动监控
    }

    public void submit(Runnable runnable) {
        if (destroy)
            throw new IllegalStateException("线程池不允许再提交任务");
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard(); // 执行拒绝策略
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    // 线程池的线程
    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool#Min:%d, Active:%d, Max:%d, Current:%d, QueueSize: %d\n",
                this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5000);
                if (TASK_QUEUE.size() > active && size < active) { // 扩到active
                    for (int i = size; i < active; i++) {
                        createWorkerTask(); // 增加线程
                    }
                    System.out.println("线程池容量增加到 active:" + active + " 了");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) { // 扩大到max, // TODO 需要回收
                    for (int i = size; i < max; i++) {
                        createWorkerTask(); // 增加线程
                    }
                    System.out.println("线程池容量增加到 max:" + max + " 了");
                    size = max;
                }

                // 准备回收
                synchronized (THREAD_QUEUE) { // 防止其他线程
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("====================Reduce=================");

                        int releaseSize = size - active; // 需要释放的线程数量
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkerTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createWorkerTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        THREAD_QUEUE.add(task);
        task.start();
    }


    // 停止任务
    // 等剩余任务结束, 这边会阻塞/
    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }

        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkerTask task : THREAD_QUEUE) {
                if (task.getTaskState() == TaskState.BLOCKED) { //将blocked的线程给停了
                    task.interrupt(); // 中断blocked的线程
                    task.close();
                    initVal--;
                } else {
                    Thread.sleep(10);
                }
            }
        }

        System.out.println(GROUP.activeCount());

        this.destroy = true;
        System.out.println("线程池 关闭了...");
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    // 单个执行线程, 里面while循环处理数据
    private static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public TaskState getTaskState() {
            return this.taskState;
        }

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Close Thread:" + Thread.currentThread().getName());
                            break OUTER;
                        }

                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        runnable = null;
                    }
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool threadPool = new SimpleThreadPool();
        for (int i = 0; i < 40; i++) {
            int j = i;
            threadPool.submit(() -> {
                System.out.println("当前runnable " + j + " 被 " + Thread.currentThread().getName() + " 开始执行...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前runnable " + j + " 被 " + Thread.currentThread().getName() + " 执行结束!!!");
            });
        }


        Thread.sleep(100000);
        threadPool.shutdown();
        // 停掉线程池之后再提交...
        threadPool.submit(() -> {
            System.out.println("提交了一个Runnable任务");
        });
    }
}
