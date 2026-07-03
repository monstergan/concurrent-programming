package com.monster.base;

import com.monster.utils.SleepTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadJoinDemo {
    private static final Logger log = LoggerFactory.getLogger(ThreadJoinDemo.class);

    private static int count = 0;

    public static void main(String[] args) {
        log.debug("main 线程开始执行");

        Thread thread = new Thread(() -> {
            log.debug("子线程开始执行");
            SleepTools.second(1);
            count = 5;
            log.debug("子线程执行完成");
        }, "t1");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("main 线程等待子线程时被中断", e);
        }

        log.debug("结果为：{}", count);
        log.debug("main 线程执行完成");
    }
}
