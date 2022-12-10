package com.wangwenjun.concurrent.chapter18_active_objects.client;

import com.wangwenjun.concurrent.chapter18_active_objects.ActiveObject;
import com.wangwenjun.concurrent.chapter18_active_objects.ActiveObjectFactory;

/**
 * @author malichun
 * @create 2022/12/06 0006 0:28
 */
public class ActiveObjectClient {
    // A a -> B b
    // future
    public static void main(String[] args) {
        //System.gc(); // 别的线程执行
        //System.gc(); // 忽略

        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread(activeObject, "Alick").start();
        new MakerClientThread(activeObject, "Bobby").start();;

        new DisplayClientThread("Chris", activeObject).start();

    }
}
