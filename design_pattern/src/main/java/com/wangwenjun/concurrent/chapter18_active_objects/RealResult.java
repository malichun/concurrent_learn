package com.wangwenjun.concurrent.chapter18_active_objects;

/**
 * @author malichun
 * @create 2022/12/06 0006 12:56
 */
public class RealResult implements Result{
    private final Object resultValue;

    public RealResult(Object resultValue){
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return this.resultValue;
    }
}
