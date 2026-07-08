package com.monster.threadlocal;

import java.lang.ref.WeakReference;

public class ReferenceDemo {

    public static void main(String[] args) {

        // 强引用 只要变量存在，对象就不会被垃圾回收
        String hello = new String("Hello");
        System.gc();
        System.out.println(hello);

        //  弱引用 只要垃圾回收器运行，对象就有可能被回收
        WeakReference<String> weakReference = new WeakReference<>(new String("Hello World"));
        System.out.println("执行垃圾回收之前");
        System.out.println(weakReference.get());
        System.gc();
        System.out.println("执行垃圾回收之后");
        System.out.println(weakReference.get());
    }
}
