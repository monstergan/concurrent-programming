package com.monster.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 实现最优的“烧水泡茶”程序
 */
public class CompletableFutureDemo2 {

    private static final Logger log = LoggerFactory.getLogger(CompletableFutureDemo2.class);

    public static void main(String[] args) {

        CompletableFuture<Void> ft1 = CompletableFuture.runAsync(() -> {
            log.debug("T1:洗水壶....");
            sleep(1, TimeUnit.SECONDS);

            log.debug("T1:烧开水....");
            sleep(15, TimeUnit.SECONDS);
        });

        // 任务 2：洗茶壶 --> 洗茶杯 --> 拿茶叶
        CompletableFuture<String> ft2 = CompletableFuture.supplyAsync(() -> {
            log.debug("T2:洗茶壶....");
            sleep(1, TimeUnit.SECONDS);

            log.debug("T2:洗茶杯....");
            sleep(2, TimeUnit.SECONDS);

            log.debug("T2:拿茶叶....");
            sleep(1, TimeUnit.SECONDS);

            return "龙井";
        });

        //  任务 3：任务 1 和任务 2 完成之后执行 泡茶
        CompletableFuture<String> ft3 = ft1.thenCombine(ft2, (__, tea) -> {

            log.debug("T1:水烧好了并且拿到茶叶：{}", tea);
            log.debug("T1:进行泡茶....");
            return "上茶" + tea;
        });

        log.debug(ft3.join());
    }

    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }
}
