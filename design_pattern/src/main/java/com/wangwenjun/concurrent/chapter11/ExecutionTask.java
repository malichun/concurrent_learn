package com.wangwenjun.concurrent.chapter11;

/**
 * @author malichun
 * @create 2022/11/26 0026 16:22
 */
public class ExecutionTask implements Runnable{

    private QueryFromDBAction queryAction = new QueryFromDBAction();

    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        // 执行活成中不会看到context的定义和声明
        queryAction.execute();
        System.out.println("The name query successful");
        httpAction.execute();
        System.out.println("The card is query successful");

        final Context context = ActionContext.getActionContext().getContext();
        System.out.println("The name is :" + context.getName() + " and CardId "+context.getCardId());


    }
}
