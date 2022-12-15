package com.wangwenjun.juc.atomic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author malichun
 * @create 2022/12/14 0014 19:40
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<Simple> atomic = new AtomicReference<>(new Simple("Alex",12));

        boolean result = atomic.compareAndSet(new Simple("sdfs", 22), new Simple("sdf", 234));
        System.out.println(result);

        JButton button = new JButton();

        // defaultValue
        AtomicReference<Simple> s = new AtomicReference<Simple>(new Simple("test", 12));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // invoke restful service
                // s = new Simple("sdfs",24)
                s.set(new Simple("qwe",324));
            }
        });


    }

    static class ObjectWrap<T>{

        private T t;

        public ObjectWrap(T t){
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

    }

    static class Simple{
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
