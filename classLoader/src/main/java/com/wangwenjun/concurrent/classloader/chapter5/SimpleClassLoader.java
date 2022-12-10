package com.wangwenjun.concurrent.classloader.chapter5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author malichun
 * @create 2022/12/10 0010 17:36
 */
public class SimpleClassLoader extends ClassLoader {

    private final static String DEFAULT_DIR = "D:\\projects\\concurrent_learn\\app\\revert\\";

    private String dir = DEFAULT_DIR;

    private String classLoaderName;

    public SimpleClassLoader() {
        super();
    }

    public SimpleClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public SimpleClassLoader(String classLoaderName, ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }

    /**
     * 类的全类名 xxx.xxx.xxx.xxx.AAA
     * xxx/xxx/xxx/xxx/AAA
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        File classFile = new File(dir+classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + " not found under " + dir);
        }

        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + " failed");
        }
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * 先用子类的类加载器的findClass
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = null;

        // 如果是java自带的, 还是用它自己的加载器
        if(name.startsWith("java.")){
            try{
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);
                if(clazz!=null){
                    if(resolve){
                        resolveClass(clazz);
                    }
                    return clazz;
                }
            }catch (Exception e){
                // ignore
            }
        }

        try{
            // 这边是自己的findClass
            clazz = findClass(name);
        }catch (Exception e){

        }

        // 其他的用系统加载器
        if(clazz==null && getParent()!=null){
            getParent().loadClass(name); // 调用父亲的类加载器
        }

        return clazz;
    }

    /**
     * 把一个文件变成一个数组
     *
     * @param classFile
     * @return
     */
    private byte[] loadClassBytes(File classFile) {
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(classFile);
        ) {
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer))!= -1){
                baos.write(buffer,0,len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}

