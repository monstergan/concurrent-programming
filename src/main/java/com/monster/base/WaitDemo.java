package com.monster.base;

public class WaitDemo {

    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();

        Thread t1 = new Thread(() -> {

            System.out.println("wait 开始");

            synchronized (locker) {
                try {
                    locker.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            System.out.println("wait 结束");
        }, "t1");

        t1.start();

        // 保证t1 先启动，wait() 先执行
        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            synchronized (locker) {
                System.out.println("notfy 开始");
                locker.notifyAll();
                System.out.println("notfy 结束");
            }
        }, "t2");

        t2.start();
    }
}
