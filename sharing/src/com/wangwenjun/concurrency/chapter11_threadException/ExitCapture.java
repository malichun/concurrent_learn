package com.wangwenjun.concurrency.chapter11_threadException;

/**
 * 报错即将退出时, 会增加hook来释放资源
 * @author malichun
 * @create 2022/11/20 0020 14:31
 */
public class ExitCapture{
    public static void main(String[] args){
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            System.out.println("应用即将退出...");
            notifyAndRelease();
        }));

        int i = 0;
        while(true){
            try{
                Thread.sleep(1000L);
                System.out.println("I am working...");
            }catch(Throwable e){
                // ignore
            }
            i++;
            if(i>20) throw new RuntimeException("Error...");
        }
    }

    private static void notifyAndRelease(){
        // 打印
        System.out.println("notify to the admin.");
        try{
            Thread.sleep(1000);
        }catch(Throwable e){

        }
        System.out.println("will release resource(socket,file,connection.)");
        try{
            Thread.sleep(1000);
        }catch(Throwable e){

        }
        System.out.println("Release and notify done.");
    }
}

