package com.threadPoolExecutorDemo;

import com.utils.DateUtil;

import java.util.Date;

/**
 * 这是一个简单的Runnable类，需要大约5秒钟来执行其任务。
 * <p>
 * 为了让大家更清楚上面的面试题中的一些概念，我写了一个简单的线程池 Demo。
 * <p>
 * 首先创建一个 Runnable 接口的实现类（当然也可以是 Callable 接口，我们上面也说了两者的区别。）
 *
 * @author LC
 */
public class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date() + " 开始时间: " + DateUtil.currentStr() + " 数据: " + this.command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date() + " 结束时间: " + DateUtil.currentStr() + " 数据: " + this.command);
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}