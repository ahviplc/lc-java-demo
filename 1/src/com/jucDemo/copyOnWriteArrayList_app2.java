package com.jucDemo;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CopyOnWriteArrayList是线程安全的集合。本身就是安全的，同时只能被一个进程所访问。
 * <p>
 * CopyOnWriteArrayList线程安全的集合 - li33的博客 - 博客园
 * https://www.cnblogs.com/li33/p/12719232.html
 */
public class copyOnWriteArrayList_app2 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
        //最常用的一个类就是AtomicInteger，可以把它看作一个整数。与Integer不同，它是可变的，并且是线程安全的。对其进行修改等任何操作都是用CAS指令进行的.
        AtomicInteger tempAtomicInteger = new AtomicInteger();
        //将值置成0
        tempAtomicInteger.set(0);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                copyOnWriteArrayList.add(Thread.currentThread().getName() + " id: " + Thread.currentThread().getId() + " tempInt: " + tempAtomicInteger.getAndIncrement());
            }).start();//记得要启动线程
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(copyOnWriteArrayList.size());
        System.out.println(copyOnWriteArrayList.get(0));
        System.out.println(copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1));
        System.out.println(tempAtomicInteger.get());
        System.out.println(tempAtomicInteger.toString());
    }
}
