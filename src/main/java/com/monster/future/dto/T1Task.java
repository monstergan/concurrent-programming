package com.monster.future.dto;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T1Task implements Callable<String> {

    FutureTask<String> ft2;

    //  T1 任务需要 T2 任务的 FutureTask
    public T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1:洗水壶....");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1:烧开水");
        TimeUnit.SECONDS.sleep(15);

        //  获取 T2 线程的茶叶
        String tea = ft2.get();
        System.out.println("T1:水烧好了并拿到茶叶：" + tea);
        System.out.println("T1:泡茶....");
        return "上茶：" + tea;
    }
}
