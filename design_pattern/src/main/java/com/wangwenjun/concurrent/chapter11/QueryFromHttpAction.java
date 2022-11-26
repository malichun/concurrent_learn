package com.wangwenjun.concurrent.chapter11;

/**
 * @author malichun
 * @create 2022/11/26 0026 16:50
 */
public class QueryFromHttpAction {
    public void execute(){
        // 请求http服务
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "2323232328989" + Thread.currentThread().getId();
    }
}
