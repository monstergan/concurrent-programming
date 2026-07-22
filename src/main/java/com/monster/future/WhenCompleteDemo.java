package com.monster.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
public class WhenCompleteDemo {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (new Random().nextInt() % 2 == 0) {
                int i = 12 / 0;
            }
            log.info("执行结果！");
            return "test";
        });

//        completableFuture.whenComplete(new BiConsumer<String, Throwable>() {
//            @Override
//            public void accept(String s, Throwable throwable) {
//                log.debug(s + "执行完成！");
//            }
//        });

        completableFuture.exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                log.debug("执行失败：{}", throwable.getMessage());
                return "异常......";
            }
        }).join();
    }
}
