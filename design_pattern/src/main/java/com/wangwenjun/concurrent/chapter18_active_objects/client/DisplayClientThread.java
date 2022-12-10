package com.wangwenjun.concurrent.chapter18_active_objects.client;

import com.wangwenjun.concurrent.chapter18_active_objects.ActiveObject;

/**
 * @author malichun
 * @create 2022/12/07 0007 21:15
 */
public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                String text = Thread.currentThread().getName() + "=>" + i;
                activeObject.displayString(text);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
