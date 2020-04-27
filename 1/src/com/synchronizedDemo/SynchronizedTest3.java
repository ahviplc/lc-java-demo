package com.synchronizedDemo;

import com.utils.DateUtil;

/**
 * 一、synchronized作用于实例方法
 * ③一个线程获取了该对象的锁之后，其他线程来访问其他非synchronized实例方法现象 举栗
 * 去掉②中方法二的synchronized
 */
public class SynchronizedTest3 {
    public synchronized void method1() {
        System.out.println(DateUtil.currentStr() + " Method 1 start");
        try {
            System.out.println(DateUtil.currentStr() + " Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.currentStr() + " Method 1 end");
    }

    public void method2() {
        System.out.println(DateUtil.currentStr() + " Method 2 start");
        try {
            System.out.println(DateUtil.currentStr() + " Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.currentStr() + " Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest3 test = new SynchronizedTest3();
        new Thread(test::method1).start();
        new Thread(test::method2).start();
    }

//输出demo:
//2020-04-27 11:11:48 Method 1 start
//2020-04-27 11:11:48 Method 2 start
//2020-04-27 11:11:48 Method 1 execute
//2020-04-27 11:11:48 Method 2 execute
//2020-04-27 11:11:49 Method 2 end
//2020-04-27 11:11:51 Method 1 end

//分析：当线程1还在执行时，线程2也执行了，所以当其他线程来访问非synchronized修饰的方法时是可以访问的
}
