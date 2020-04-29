package com.diffSynchronizedAndLockDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock
 * <p>
 * 输出demo:
 * 可恶的黄牛买到第10张票
 * 苦逼的她们买到第9张票
 * 苦逼的她们买到第8张票
 * 苦逼的我买到第7张票
 * 可恶的黄牛买到第6张票
 * 可恶的黄牛买到第5张票
 * 苦逼的她们买到第4张票
 * 苦逼的我买到第3张票
 * 可恶的黄牛买到第2张票
 * 苦逼的她们买到第1张票
 * <p>
 * demo2:
 * 可恶的黄牛买到第10张票
 * 苦逼的她们买到第9张票
 * 苦逼的我买到第8张票
 * 苦逼的我买到第7张票
 * 苦逼的我买到第6张票
 * 苦逼的我买到第5张票
 * 可恶的黄牛买到第4张票
 * 苦逼的她们买到第3张票
 * 苦逼的我买到第2张票
 * 可恶的黄牛买到第1张票
 */
public class TestLock {
    public static void main(String[] args) {
        Person3 person3 = new Person3();
        new Thread(person3, "可恶的黄牛").start();
        new Thread(person3, "苦逼的她们").start();
        new Thread(person3, "苦逼的我").start();
    }
}

class Person3 implements Runnable {
    //定义lock锁
    //公平锁是指当锁可用时,在锁上等待时间最长的线程将获得锁的使用权。而非公平锁则随机分配这种使用权。
    //和synchronized一样，默认的ReentrantLock实现是非公平锁,因为相比公平锁，非公平锁性能更好。
    //当然公平锁能防止饥饿,某些情况下也很有用。在创建ReentrantLock的时候通过传进参数true创建公平锁,如果传入的是false或没传参数则创建的是非公平锁
    private ReentrantLock lock = new ReentrantLock();
    int ticketNums = 10;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                lock.lock();
                Thread.sleep(100);
                if (ticketNums > 0) {
                    System.out.println(Thread.currentThread().getName() + "买到第" + ticketNums-- + "张票");
                } else {
                    flag = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
