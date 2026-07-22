package com.monster.threadpool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable = new MyCallableTask();
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        Thread thread = new Thread(future);
        thread.start();
        Thread.sleep(100);
        //尝试取消对此任务的执行
        future.cancel(true);
        //判断是否在任务正常完成前取消
        System.out.println("future is cancel:" + future.isCancelled());
        if (!future.isCancelled()) {
            System.out.println("future is cancelled");
        }
        //判断任务是否已完成
        System.out.println("future is done:" + future.isDone());
        if (!future.isDone()) {
            System.out.println("future get=" + future.get());
        } else {
            //任务已完成
            System.out.println("task is done");
        }
    }

    static class MyCallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("callable do somothing");
            Thread.sleep(5000);
            return new Random().nextInt(100);
        }
    }
}
