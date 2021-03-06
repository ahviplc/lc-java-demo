package com.lc.TimerTask;

import com.utils.DateUtil;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * ScheduledExecutorService是从Java SE5的java.util.concurrent里，做为并发工具类被引进的，这是最理想的定时任务实现方式。
 * 相比于上两个方法TimeTaskTest2 TimeTaskTest3，它有以下好处：
 * 1>相比于Timer的单线程，它是通过线程池的方式来执行任务的
 * 2>可以很灵活的去设定第一次执行任务delay时间
 * 3>提供了良好的约定，以便设定执行的时间间隔
 * <p>
 * 下面是实现代码，我们通过ScheduledExecutorService#scheduleAtFixedRate展示这个例子，通过代码里参数的控制，首次执行加了delay时间。
 *
 * @author LC
 */


public class TimeTaskTest5 {
    public static void main(String[] args) {
        //输出当前时间
        System.out.println("----------------------------------------------------");
        System.out.println(DateUtil.currentStr());
        Runnable runnable = new Runnable() {

            public void run() {
                // task to run goes here
                System.out.println("Hello LC!!!");
                //输出当前时间
                System.out.println(DateUtil.currentStr());
                //再延迟5秒
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello End !!!");
                System.out.println(DateUtil.currentStr());
                System.out.println("----------------------------------------------------");
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
    }
}
