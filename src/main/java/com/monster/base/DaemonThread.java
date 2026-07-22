package com.monster.base;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        log.debug("开始运行。。。。");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("开始运行....");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("运行结束....");
            }
        }, "t1");

        // 设置 T1 为守护线程
        t1.setDaemon(true);
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        log.debug("运行结束。。。。。");
    }

}
