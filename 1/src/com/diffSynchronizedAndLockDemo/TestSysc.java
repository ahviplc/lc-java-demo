package com.diffSynchronizedAndLockDemo;

/**
 * 使用synchronized关键字
 * <p>
 * 输出demo:
 * 可恶的黄牛买到第10张票
 * 可恶的黄牛买到第9张票
 * 可恶的黄牛买到第8张票
 * 可恶的黄牛买到第7张票
 * 可恶的黄牛买到第6张票
 * 可恶的黄牛买到第5张票
 * 可恶的黄牛买到第4张票
 * 可恶的黄牛买到第3张票
 * 可恶的黄牛买到第2张票
 * 可恶的黄牛买到第1张票
 * <p>
 * demo2:
 * 苦逼的她们买到第10张票
 * 苦逼的她们买到第9张票
 * 苦逼的她们买到第8张票
 * 苦逼的她们买到第7张票
 * 苦逼的她们买到第6张票
 * 苦逼的她们买到第5张票
 * 苦逼的她们买到第4张票
 * 苦逼的她们买到第3张票
 * 苦逼的她们买到第2张票
 * 苦逼的她们买到第1张票
 */
public class TestSysc {
    public static void main(String[] args) {
        Person person = new Person();
        new Thread(person, "可恶的黄牛").start();
        new Thread(person, "苦逼的她们").start();
        new Thread(person, "苦逼的我").start();
    }
}

class Person implements Runnable {
    int ticketNums = 10;
    private boolean flag = true;

    @Override
    public synchronized void run() {
        while (flag) {
            if (ticketNums > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "买到第" + ticketNums-- + "张票");
            } else {
                flag = false;
            }
        }
    }
}
