package com.wangwenjun.concurrency.chapter9_producerconsumer;

import java.util.*;
import java.util.stream.Stream;

/**
 * 类似线程池的实现, 限制线程数的使用
 * @author malc
 * @create 2022/11/19 0019 22:40
 */
public class CaptureService {

    final private static LinkedList<Control> CONTROLS = new LinkedList<>();
    final private static int MAX_WORKER = 5;

    public static void main(String[] args) {
        List<Thread> workers = new ArrayList<>();

        Stream.of("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
            .map(CaptureService::createCaptureThread)
            .forEach(t -> {
                t.start();
                workers.add(t);
            });

        workers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("所有的计算任务都完成了").ifPresent(System.out::println);

    }

    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("worker[" + Thread.currentThread().getName() + "] 开始计算").ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() >= MAX_WORKER) {
                    try {
                        CONTROLS.wait(); // 如果大于5, 就等着
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }
            Optional.of("worker: [" + Thread.currentThread().getName() + "] 正在工作").ifPresent(System.out::println);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS){
                Optional.of("worker: [" + Thread.currentThread().getName() + "] 完成了").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        }, name);
    }

    private static class Control {
    }
}
