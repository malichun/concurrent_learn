package com.wangwenjun.concurrent.classloader.chapter1;

/**
 * 类的初始化
 */
public class ClassActiveUse {

    static{
        System.out.println("启动类...");
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //new Obj();
        //System.out.println(I.a);

        //System.out.println(Obj.salary);
        //Obj.printSalary();

        //Class<Obj> objClass = Obj.class;
        //objClass.newInstance();

        System.out.println(Obj.salary); // 调用类的 final修饰的变量 不会导致初始化

    }

}

class Obj{

    public static final long salary = 100000;

    static {
        System.out.println("Obj被初始化");
    }

    public static void printSalary(){
        System.out.println("==========obj==salary======");
    }
}
interface I{
    int a = 10;
}

// 访问某个类或者接口的静态变量, 或者对该静态变量进行赋值操作
//1.对某个类的静态变量进行读写  -> class
//2.对接口中静态变量进行读取    -> interface
