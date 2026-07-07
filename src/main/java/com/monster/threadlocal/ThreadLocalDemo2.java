package com.monster.threadlocal;

public class ThreadLocalDemo2 {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String context;

    public String getContext() {
        return threadLocal.get();
    }

    public void setContext(String context) {
        threadLocal.set(context);
    }

    public static void main(String[] args) {
        ThreadLocalDemo2 demo = new ThreadLocalDemo2();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContext(Thread.currentThread().getName() + " 的数据");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContext());
                }
            });
            thread.setName("线程 " + i);
            thread.start();
        }
    }
}
