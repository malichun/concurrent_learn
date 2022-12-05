package com.wangwenjun.concurrent.chapter16_two_phase_terminate;

import java.io.IOException;

/**
 * @author malichun
 * @create 2022/12/05 0005 22:29
 */
public class AppServerClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        AppServer server = new AppServer(13345);
        server.start();

        Thread.sleep(45000);
        server.shutdown();
    }
}
