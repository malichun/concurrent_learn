package com.wangwenjun.concurrency.chapter1;

/**
 * @author malichun
 * @create 2022/11/15 0015 0:40
 */
public abstract class TemplateMethod {
    public final void print(String message){
        System.out.println("######################");
        wrapPrint(message); // 模版方法
        System.out.println("######################");
    }

    protected void wrapPrint(String message){

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "*");
            }
        };
        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+" + message + "+");
            }
        };
        t2.print("Hello Thread");
    }
}

