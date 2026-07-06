package com.monster.base;

public class VolatileDemo {

    private static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("线程 t1 开始执行");
                int i = 0;
                while (!stop) {
                    i++;
                }
                System.out.println("跳出循环");
            }
        }, "t1");

        t1.start();

        Thread.sleep(1000);
        stop = true;
        System.out.printf("线程 t1 执行完成，stop = %s%n", stop);
    }
}
