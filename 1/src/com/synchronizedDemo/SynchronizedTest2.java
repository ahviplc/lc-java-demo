package com.synchronizedDemo;

import com.utils.DateUtil;

/**
 * 一、synchronized作用于实例方法
 * ②一个线程获取了该对象的锁之后，其他线程来访问其他synchronized实例方法现象 举栗
 */
public class SynchronizedTest2 {
    public synchronized void method1() {
        System.out.println(DateUtil.currentStr()+" Method 1 start");
        try {
            System.out.println(DateUtil.currentStr()+" Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.currentStr()+" Method 1 end");
    }

    public synchronized void method2() {
        System.out.println(DateUtil.currentStr()+" Method 2 start");
        try {
            System.out.println(DateUtil.currentStr()+" Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.currentStr()+" Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest2 test = new SynchronizedTest2();

        new Thread(test::method1).start();

        new Thread(test::method2).start();
    }

//输出Demo:
//2020-04-27 11:10:56 Method 1 start
//2020-04-27 11:10:56 Method 1 execute
//2020-04-27 11:10:59 Method 1 end
//2020-04-27 11:10:59 Method 2 start
//2020-04-27 11:10:59 Method 2 execute
//2020-04-27 11:11:00 Method 2 end

//分析：可以看出其他线程来访问synchronized修饰的其他方法时需要等待线程1先把锁释放
}
