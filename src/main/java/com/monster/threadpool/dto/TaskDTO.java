package com.monster.threadpool.dto;

public class TaskDTO implements Runnable {

    @Override
    public void run() {
        System.out.println("进入了task方法！！！");
        int i=1/0;
    }
}
