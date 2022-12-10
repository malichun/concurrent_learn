package com.wangwenjun.concurrent.chapter18_active_objects;

import java.util.Arrays;

/**
 * @author malichun
 * @create 2022/12/06 0006 12:53
 */
class Servant implements ActiveObject{
    @Override
    public Result makeString(int count, char fillChar) {
        char[] buf = new char[count];
        Arrays.fill(buf,fillChar);
        try{
            Thread.sleep(10);
        }catch (Exception e){

        }
        return new RealResult(new String(buf));
    }

    @Override
    public void displayString(String text) {
        try{
            System.out.println("Display:"+text);
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
