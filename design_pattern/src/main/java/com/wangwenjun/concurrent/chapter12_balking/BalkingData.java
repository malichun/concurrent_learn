package com.wangwenjun.concurrent.chapter12_balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author malichun
 * @create 2022/11/26 0026 18:12
 */
public class BalkingData {
    private final String fileName;
    private String content;

    private boolean changed;

    public BalkingData(String fileName, String content){
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent){
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        // 还没有修改,退出
        if(!changed){
            return;
        }
        // 如果修改了, 那么保存
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName()+" calls do save,content="+content);
        try( Writer writer = new FileWriter(fileName,true)){
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }

    }

}
