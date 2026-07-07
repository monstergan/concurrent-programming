package com.monster.future;

import com.monster.future.dto.T1Task;
import com.monster.future.dto.T2Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现最优的“烧水泡茶”程序
 */
public class FutureTaskDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务 T2 的 FutureTask
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        //  创建任务 T2 的 FutureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        // 线程 T1 执行任务 ft1
        Thread t1 = new Thread(ft1);
        t1.start();

        // 线程 T2 执行任务 ft2
        Thread t2 = new Thread(ft2);
        t2.start();

        // 等待线程 T1 执行结果
        System.out.println(ft1.get());
    }

}
