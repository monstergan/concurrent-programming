package com.monster.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadJoinDemo2 {

    private static final Logger log = LoggerFactory.getLogger(ThreadJoinDemo2.class);

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("线程 t1 执行完成");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    t1.interrupt();
                    throw new RuntimeException(e);
                }
                log.debug("线程 t2 执行完成");
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    t2.interrupt();
                    throw new RuntimeException(e);
                }
                log.debug("线程 t3 执行完成");
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
