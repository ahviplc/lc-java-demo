package com.synchronizedDemo;

import com.utils.DateUtil;

/**
 * 原文链接：https://blog.csdn.net/zjy15203167987/java/article/details/82531772
 *
 * 1.为什么要使用synchronized
 *
 * 在并发编程中存在线程安全问题，主要原因有：1.存在共享数据 2.多线程共同操作共享数据。关键字synchronized可以保证在同一时刻，只有一个线程可以执行某个方法或某个代码块，同时synchronized可以保证一个线程的变化可见（可见性），即可以代替volatile。
 *
 * 2.实现原理
 *
 * synchronized可以保证方法或者代码块在运行时，同一时刻只有一个方法可以进入到临界区，同时它还可以保证共享变量的内存可见性
 *
 * 3.synchronized的三种应用方式
 *
 * Java中每一个对象都可以作为锁，这是synchronized实现同步的基础：
 *
 * 普通同步方法（实例方法），锁是当前实例对象 ，进入同步代码前要获得当前实例的锁
 * 静态同步方法，锁是当前类的class对象 ，进入同步代码前要获得当前类对象的锁
 * 同步方法块，锁是括号里面的对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
 * 4.synchronized的作用
 *
 * Synchronized是Java中解决并发问题的一种最常用最简单的方法 ，他可以确保线程互斥的访问同步代码
 *
 * 5.举栗子
 *
 * 一、synchronized作用于实例方法
 *  ①多个线程访问同一个对象的同一个方法
 */
public class SynchronizedTest implements Runnable {
    //共享资源
    static int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(DateUtil.currentStr() +" 输出: "+ i);//2020-04-27 11:05:21 输出: 20000 注意:将t2隐掉 输出:2020-04-27 11:05:21 输出: 10000
    }
//    分析：当两个线程同时对一个对象的一个方法进行操作，只有一个线程能够抢到锁。
//    因为一个对象只有一把锁，一个线程获取了该对象的锁之后，其他线程无法获取该对象的锁，就不能访问该对象的其他synchronized实例方法，
//    但是可以访问非synchronized修饰的方法
}
