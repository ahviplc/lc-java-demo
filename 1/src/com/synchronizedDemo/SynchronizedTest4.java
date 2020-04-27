package com.synchronizedDemo;

import com.utils.DateUtil;

/**
 * 一、synchronized作用于实例方法
 * ④当多个线程作用于不同的对象
 */
public class SynchronizedTest4 {
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

    public synchronized void method2() {
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
        final SynchronizedTest4 test1 = new SynchronizedTest4();
        final SynchronizedTest4 test2 = new SynchronizedTest4();
        new Thread(test1::method1).start();
        new Thread(test2::method2).start();
    }

//输出demo:
//2020-04-27 11:15:52 Method 2 start
//2020-04-27 11:15:52 Method 1 start
//2020-04-27 11:15:52 Method 2 execute
//2020-04-27 11:15:52 Method 1 execute
//2020-04-27 11:15:53 Method 2 end
//2020-04-27 11:15:55 Method 1 end

//分析：因为两个线程作用于不同的对象，获得的是不同的锁，所以互相并不影响
}
