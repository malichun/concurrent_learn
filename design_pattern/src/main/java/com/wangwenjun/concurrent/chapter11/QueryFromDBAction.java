package com.wangwenjun.concurrent.chapter11;

/**
 * @author malichun
 * @create 2022/11/26 0026 16:23
 */
public class QueryFromDBAction {

    // 消灭了传参数
    public void execute(){
        // 从数据库拿了些数据
        try {
            Thread.sleep(1000L);
            String name = "Alex " + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
