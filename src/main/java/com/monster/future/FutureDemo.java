package com.monster.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureDemo {

    private static final Logger log = LoggerFactory.getLogger(FutureDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("通过 Runnable 方式执行任务");
            }
        }, "t1");
        t1.start();

        FutureTask task = new FutureTask<>(new Callable() {
            @Override
            public Object call() throws Exception {
                log.debug("通过 Callable 方式执行任务");
                Thread.sleep(3000);
                return "返回任务结果";
            }
        });

        new Thread(task).start();
        log.debug("结果是：{}", task.get());
    }
}
