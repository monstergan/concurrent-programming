package com.monster.future;

import java.util.concurrent.*;

public class FutureTaskDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> ft1 = new FutureTask<>(new T1Task());
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        FutureTask<String> ft3 = new FutureTask<>(new T3Task());
        FutureTask<String> ft4 = new FutureTask<>(new T4Task());
        FutureTask<String> ft5 = new FutureTask<>(new T5Task());

        // 构建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(ft1);
        executorService.submit(ft2);
        executorService.submit(ft3);
        executorService.submit(ft4);
        executorService.submit(ft5);
        //  获取执行结果
        System.out.println(ft1.get());
        System.out.println(ft2.get());
        System.out.println(ft3.get());
        System.out.println(ft4.get());
        System.out.println(ft5.get());

        executorService.shutdown();
    }

    static class T1Task implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("t1 查询基本信息....");
            TimeUnit.MILLISECONDS.sleep(5000);
            return "基础商品信息查询成功";
        }
    }

    static class T2Task implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("T2：查询价格信息....");
            TimeUnit.MILLISECONDS.sleep(50);
            return "价格信息查询成功";
        }
    }

    static class  T3Task implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("T3：查询商品库存信息....");
            TimeUnit.MILLISECONDS.sleep(50);
            return "库存信息查询成功";
        }
    }

    static class T4Task implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("T4：查询商品图片信息....");
            TimeUnit.MILLISECONDS.sleep(50);
            return "商品图片信息查询成功";
        }
    }

    static class T5Task implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("T5：查询商品评价信息....");
            TimeUnit.MILLISECONDS.sleep(50);
            return "商品评价信息查询成功";
        }
    }
}
