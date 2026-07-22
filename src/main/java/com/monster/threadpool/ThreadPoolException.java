package com.monster.threadpool;

import com.monster.threadpool.dto.TaskDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class ThreadPoolException {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //  创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 当线程池抛出异常后 submit 无法提示 ，其他线程继续执行
        Future<?> submit = executorService.submit(new TaskDTO());
        submit.get();

        //当线程池抛出异常后 execute抛出异常，其他线程继续执行新任务
//        executorService.execute(new TaskDTO());
    }
}
