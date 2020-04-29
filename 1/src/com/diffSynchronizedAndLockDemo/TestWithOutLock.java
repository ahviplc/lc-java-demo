package com.diffSynchronizedAndLockDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized与lock的区别 - 一生。 - 博客园
 * https://www.cnblogs.com/seekknowledge/p/11807475.html
 *
 * synchronized与lock的区别
 * synchronized和Lock都可以控制线程的同步，同一时间只能有一个线程操作上锁资源。
 * <p>
 * Lock是一个接口，而synchronized是Java中的关键字。
 * <p>
 * 1.Lock是显式锁，可以手动开启和关闭，切结必须手动关闭，不然容易造成线程死锁。synchronized是隐式锁，出了作用域自动释放。
 * <p>
 * 2.Lock只有代码块锁，而synchronized既有代码块锁，也有方法锁。
 * <p>
 * 3.Lock锁性能更好，JVM花费少量的时间来调度线程。
 * <p>
 * 未上锁资源
 * <p>
 * 输出demo:
 * 可恶的黄牛买到第10张票
 * 苦逼的她们买到第9张票
 * 苦逼的我买到第8张票
 * 苦逼的她们买到第7张票
 * 可恶的黄牛买到第6张票
 * 苦逼的我买到第7张票
 * 可恶的黄牛买到第4张票
 * 苦逼的我买到第5张票
 * 苦逼的她们买到第3张票
 * 苦逼的她们买到第2张票
 * 苦逼的我买到第1张票
 * 可恶的黄牛买到第2张票
 * <p>
 * demo2:
 * 可恶的黄牛买到第10张票
 * 苦逼的她们买到第9张票
 * 苦逼的我买到第8张票
 * 可恶的黄牛买到第6张票
 * 苦逼的我买到第5张票
 * 苦逼的她们买到第7张票
 * 可恶的黄牛买到第4张票
 * 苦逼的我买到第3张票
 * 苦逼的她们买到第4张票
 * 苦逼的我买到第2张票
 * 苦逼的她们买到第1张票
 * <p>
 * demo3:
 * 苦逼的她们买到第10张票
 * 可恶的黄牛买到第9张票
 * 苦逼的我买到第8张票
 * 苦逼的我买到第7张票
 * 苦逼的她们买到第5张票
 * 可恶的黄牛买到第6张票
 * 苦逼的我买到第4张票
 * 苦逼的她们买到第3张票
 * 可恶的黄牛买到第2张票
 * 可恶的黄牛买到第0张票
 * 苦逼的她们买到第-1张票
 * 苦逼的我买到第1张票
 * <p>
 * Process finished with exit code 0
 */
public class TestWithOutLock {
    public static void main(String[] args) {
        Person2 person2 = new Person2();
        new Thread(person2, "可恶的黄牛").start();
        new Thread(person2, "苦逼的她们").start();
        new Thread(person2, "苦逼的我").start();
    }
}

class Person2 implements Runnable {
    //定义lock锁
    private ReentrantLock lock = new ReentrantLock();
    int ticketNums = 10;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(100);
                if (ticketNums > 0) {
                    System.out.println(Thread.currentThread().getName() + "买到第" + ticketNums-- + "张票");
                } else {
                    flag = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}
