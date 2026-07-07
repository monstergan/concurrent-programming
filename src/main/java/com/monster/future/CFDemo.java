package com.monster.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFDemo {

    private static final Logger log = LoggerFactory.getLogger(CFDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> log.info("执行无返回任务的异步结果");
        // 提交一个异步任务
        CompletableFuture.runAsync(runnable);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("执行有返回值的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello World";
        });
        String result = future.get();
        log.info(result);


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
