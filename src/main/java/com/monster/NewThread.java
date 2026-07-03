package com.monster;



import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThread {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NewThread.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 is running");
            }
        });
        t1.start();


        // new Runnable()
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 is running");
            }
        };
        Thread t2 = new Thread(runnable, "t2");
        t2.start();

        // 使用 Runnable Lambda 表达式创建线程
        Runnable runnable1 = () -> {
            System.out.println("Thread 3 is running");
        };
        Thread t3 = new Thread(runnable1, "t3");
        t3.start();

        // 创建任务对象
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.info("Thread 4 is running");
            return 100;
        });
        Thread t4 = new Thread(task, "t4");
        t4.start();
        // 主线程阻塞，同步等待 task
        Integer result = task.get();
        log.info("结果是：{}", result);
    }
}
