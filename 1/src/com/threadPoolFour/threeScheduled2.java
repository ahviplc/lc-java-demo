package com.threadPoolFour;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import com.threadPoolFour.my.MyRunnableFour;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Java 四种线程池newCachedThreadPool,newFixedThreadPool,newScheduledThreadPool,newSingleThreadExecutor - 星辰之力 - 博客园
 * https://www.cnblogs.com/zhujiabin/p/5404771.html
 * <p>
 * Java基础（九）：线程池中 submit()和 execute()方法区别_wanting1024的博客-CSDN博客_java线程池submit和execute区别
 * https://blog.csdn.net/qq_31473465/article/details/89647395
 * <p>
 * 周期性线程池newScheduledThreadPool详解_ javaboy-CSDN博客_newscheduledthreadpool
 * https://blog.csdn.net/liuchangjie0112/article/details/90698401
 * <p>
 * 线程池中的scheduleAtFixedRate scheduleWithFixedDelay区别 - vance. - 博客园
 * https://www.cnblogs.com/baby-bear/p/4058534.html
 * Java 线程池
 * <p>
 * Java通过Executors提供四种线程池，分别为：
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 */
public class threeScheduled2 {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(6);
		Console.log("{} => {}", DateUtil.now(), Thread.currentThread().getName());
		for (int i = 0; i < 3; i++) {
			// 表示延迟3秒执行
			// scheduledExecutorService.schedule(new MyRunnableFour(), 3, TimeUnit.SECONDS);
			// 表示第一次执行延迟2秒执行 之后每隔3秒执行一次
			// 这个是按照固定的时间来执行，简单来说：到点执行
			// scheduledExecutorService.scheduleAtFixedRate(new MyRunnableFour(), 2, 3, TimeUnit.SECONDS);
			// 表示第一次执行延迟2秒执行 上一个任务执行完之后3秒后执行一次
			// 这个呢，是等上一个任务结束后，在等固定的时间，然后执行。简单来说：执行完上一个任务后再执行
			scheduledExecutorService.scheduleWithFixedDelay(new MyRunnableFour(), 2, 3, TimeUnit.SECONDS);
		}
	}
}
