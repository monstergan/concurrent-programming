package com.monster.base;

public class MonitorThread extends Thread {

    private volatile boolean terminated = false;

    public void run() {

        while (!terminated) {
            //  执行监控操作
            System.out.println("监控线程正在运行...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //  执行清理操作
        System.out.println("监控线程正在执行清理操作...");
        releaseResource();
    }

    public void terminate() {
        //  设置标志变量为 True ，并等待一段时间
        terminated = true;

        try {
            //  等待 5 秒，期间监控线程会检查 terminated 状态
            join(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void releaseResource() {
        System.out.println("监控线程正在释放资源和进行必要的清理工作......");
    }

    public static void main(String[] args) throws InterruptedException {
        MonitorThread thread = new MonitorThread();
        thread.start();
        Thread.sleep(10000);
        //  终止监控线程
        thread.terminate();

        Thread.sleep(100000);
    }
}
