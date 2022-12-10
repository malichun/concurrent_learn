package com.wangwenjun.concurrent.classloader.chapter4;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author malichun
 * @create 2022/12/10 0010 18:36
 */
public class DecryptClassLoader extends ClassLoader {

    private final static String DEFAULT_DIR = "D:/projects/concurrent_learn/app/classloader3/";

    private String dir = DEFAULT_DIR;

    public DecryptClassLoader() {
        super();
    }

    public DecryptClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name)
        throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        File classFile = new File(dir + classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + " not found under directory [" + dir + "]");
        }

        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + " failed");
        }
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] loadClassBytes(File classFile) {
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(classFile);
        ) {
            int data;
            while((data=fis.read())!= -1){
                baos.write(data^EncryptUtils.ENCRYPT_FACTOR);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}