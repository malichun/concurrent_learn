package com.wangwenjun.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

/**
 * @author malichun
 * @create 2022/12/14 0014 19:12
 */
public class AtomicLongTest {

    @Test
    public void testCreate(){
        AtomicLong l = new AtomicLong(100L);
        /**
         * static final boolean VM_SUPPORTS_LONG_CAS = VMSupportsCS8();
         *
         * 32位操作系统
         * long 64
         * 先拿高位再拿低位
         * high 32
         * low  32
         * 看是否支持lockless compareAndSet
         *
         *
         */
        assertEquals(100L,l.get());

    }
}
