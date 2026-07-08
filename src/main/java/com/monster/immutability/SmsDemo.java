package com.monster.immutability;

public class SmsDemo {

    public static void main(String[] args) {
        SmsInfo smsInfo = new SmsInfo("https://www.aliyun.com", 180);

        new Thread(new Runnable() {
            @Override
            public void run() {
//                smsInfo.setUrl("http://www.tencent.com");
//                try {
//                    TimeUnit.SECONDS.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                smsInfo.setMaxSizeInBytes(200);
                smsInfo.update("http://www.tencent.com", 200);
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程 2 获取短信服务商信息：" + smsInfo);
            }
        }, "t2").start();
    }
}
