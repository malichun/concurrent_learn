package com.wangwenjun.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author malichun
 * @create 2022/12/15 0015 19:49
 */
public class FailedAtomicIntegerFieldUpdaterTest {

    /**
     * 属性字段不能是私有的
     */
    @Test(expected = RuntimeException.class)
    public void testPrivateFieldAccessError(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"i");
        TestMe me = new TestMe();
        updater.compareAndSet(me, 0, 1); // 报错了, 是访问权限问题
    }

    //
    @Test
    public void testTargetObjectIsNull(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"i");
        updater.compareAndSet(null, 0, 1); // 报错: ClassCastException
    }

    /**
     * 反射的字段要存在
     */
    @Test
    public void FieldNameInvalidate(){
        AtomicIntegerFieldUpdater<TestMe> update = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i1");
        update.compareAndSet(null,0,10);
    }

    /**
     * 字段更新器
     */
    @Test
    public void testFieldTypeInvalid(){
        AtomicReferenceFieldUpdater<TestMe2, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class, Integer.class,"i");
        TestMe2 me = new TestMe2();
        updater.compareAndSet(me,null, 1);
    }


    // 要有volatile java.lang.IllegalArgumentException: Must be volatile type
    @Test
    public void testFieldIsNotVolatile(){
        AtomicReferenceFieldUpdater<TestMe2, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class, Integer.class,"i");
        TestMe2 me = new TestMe2();
        updater.compareAndSet(me,null, 1);
    }

}

class TestMe2{
    volatile Integer i;
}

class TestMe{
    // 私有的不行, 会报访问权限问题
    //private volatile int i;
    volatile int i;
}
