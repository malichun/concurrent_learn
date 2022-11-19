package com.wangwenjun.concurrency.chapter10_userdefinedlock;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 自定义锁
 * @author malc
 * @create 2022/11/19 0019 23:31
 */
public class BlockTest {
    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1","T2","T2","t4")
            .forEach(name -> {
                new Thread(() -> {
                    try {
                        booleanLock.lock(10L);
                        Optional.of(Thread.currentThread().getName()+"抢到了锁").ifPresent(System.out::println);
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Lock.TimeOutException e) {
                        e.printStackTrace();
                        Optional.of(Thread.currentThread().getName()+" time out").ifPresent(System.out::println);
                    } finally {
                        booleanLock.unlock();
                    }
                },name).start();
            });

    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName()+" is working...").ifPresent(System.out::println);
        Thread.sleep(10000);
    }
}
