package com.wangwenjun.concurrent.classloader.chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author malichun
 * @create 2022/12/10 0010 18:27
 */
public final class EncryptUtils {

    public static final byte ENCRYPT_FACTOR = (byte) 0xff;

    private EncryptUtils() {
    }

    public static void doEncrypt(String source, String target) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(target)
        ) {
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data ^ ENCRYPT_FACTOR);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doDecrypt(String source, String target){
       doEncrypt(source,target);
    }

    public static void main(String[] args) {
        //doEncrypt("app/aaa.txt","app/bbb.txt");
        //doDecrypt("app/bbb.txt","app/ccc.txt");

        doEncrypt("D:\\projects\\concurrent_learn\\app\\classloader3\\com\\wangwenjun\\concurrent\\classloader\\chapter3\\MyObject.class","D:\\projects\\concurrent_learn\\app\\classloader3\\com\\wangwenjun\\concurrent\\classloader\\chapter3\\MyObject1.class");

    }
}
