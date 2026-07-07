package com.monster.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    private static final Logger log = LoggerFactory.getLogger(CompletableFutureDemo.class);

    public static void main(String[] args) {
        log.debug("monkey 进入餐厅，点了一份西红柿炒鸡蛋");
        CompletableFuture<String> thenCombine = CompletableFuture.supplyAsync(() -> {
            log.debug("厨师炒菜");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "西红柿炒蛋好了";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            log.debug("服务员蒸饭");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "米饭好了";
        }), (dish, rice) -> {
            log.debug("服务员打饭");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return dish + " + " + rice;
        });

        log.debug("monkey 等待上菜");
        log.debug("{},monkey 开吃", thenCombine.join());

        log.debug("monkey 吃完饭了去结账，要求开发票");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            log.debug("服务员收款");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "20";
        }).thenApply(money -> {
            log.debug("服务员开发票，面额：{} 元", money);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return money + "元发票";
        });

        log.debug("monkey 接到朋友电话");
        log.debug("monk 拿到{}.准备回家", cf.join());

        log.debug("monkey 走出餐厅，来到公交车站，等待 701 路或者 702 路公交车到来");
        cf = CompletableFuture.supplyAsync(() -> {
            log.debug("701 路公交车正在赶来");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "701 路公交车到站了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            log.debug("702 路公交车正在赶来");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "702 路公交车到站了";
        }), bus -> {
            if (bus.startsWith("702")) {
                throw new RuntimeException("702 路公交车抛锚了");
            }
            return bus;
        }).exceptionally(e -> {
            log.debug(e.getMessage());
            log.debug("monkey 叫出租车");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            return "出租车到了";
        });

        log.debug("{},monkey 坐车回家", cf.join());
    }
}
