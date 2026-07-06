package com.monster.base;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerModel {

    // 共享队列
    private final Queue<Integer> queue = new LinkedList<>();

    // 队列的容量
    private final int MAX_SIZE = 10;

    // 生产者方法
    public void produce(int item) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == MAX_SIZE) {
                queue.wait();
            }

            // 生产物品
            queue.add(item);
            System.out.println("Producer：" + item);
            // 通知正在等待的消费者
            queue.notifyAll();
        }
    }

    public int consume() throws InterruptedException {

        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }

            // 消耗物品
            int item = queue.remove();
            System.out.println("Consumer：" + item);

            // 通知可能正在等待的生产者
            queue.notifyAll();
            return item;
        }

    }

    public static void main(String[] args) {
        ProducerConsumerModel model = new ProducerConsumerModel();

        // 创建并启动生产者线程
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    model.produce(i);
                    // 模拟生产延迟
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // 创建并启动消费者线程
        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    model.consume();

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

        // 等待两个线程完成

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
