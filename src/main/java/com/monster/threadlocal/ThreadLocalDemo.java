package com.monster.threadlocal;

/**
 * 线程间的数据没有隔离
 */
public class ThreadLocalDemo {

    private String content;

    private String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {

        ThreadLocalDemo demo = new ThreadLocalDemo();

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName() + " 的数据");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });

            thread.setName("线程 " + i);
            thread.start();
        }
    }
}
