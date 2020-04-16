package com.lc.TimerTask;

import com.utils.DateUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 程序中使用到了ScheduledExecutorService的scheduleAtFixedRate方法，用于定时执行任务，但是发现程序运行一段时间之后定时任务不执行了，查看日志和perfcounter都没有看到任何异常，比较郁闷。最后看了一下JDK的源码，
 * 在源码的Java doc中的发现了如下一句话：If any execution of the task encounters an exception, subsequent executions are suppressed.Otherwise, the task will only terminate via cancellation or termination of the executor.
 * 简单总结就是：如果定时任务执行过程中遇到发生异常，则后面的任务将不再执行。
 *
 * 下面使用try catch 使其在发生异常的时候 继续可以执行
 * ————————————————
 * 版权声明：本文为CSDN博主「零度anngle」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/zmx729618/java/article/details/51436274
 */
public class TimeTaskTest6 {

    private final static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    int[] s = new int[1];
                    System.out.println(DateUtil.currentStr() + " OK");
                    //System.out.println(s[1]); // 数组越界
                    throw new RuntimeException(".............................线程终止.............................");
                } catch (Exception t) {
                    System.out.println(DateUtil.currentStr() + " Error " + t.toString());
                }

            }
        }, 1, 5, TimeUnit.SECONDS);
    }
}