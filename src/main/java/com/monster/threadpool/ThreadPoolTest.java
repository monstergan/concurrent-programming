package com.monster.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 200, 100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        executor.execute(() -> System.out.println("Hello World"));
    }
}
