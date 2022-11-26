package com.wangwenjun.concurrent.chapter11;

/**
 * 单例, 使用Holder的模式
 */
public final class ActionContext {

    // 这边是用了ThreadLocal, 和线程绑定, 每个线程获取自己的独一份数据
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>(){
        // 这边使用默认值 是一个Context
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class ContextHolder{
        private final static ActionContext actionContext = new ActionContext();
    }

    private ActionContext(){}

    public static ActionContext getActionContext(){
        return ContextHolder.actionContext;
    }


    public Context getContext(){
        return threadLocal.get();
    }
}
