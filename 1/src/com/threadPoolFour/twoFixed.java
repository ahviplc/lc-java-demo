package com.threadPoolFour;

import com.threadPoolFour.my.MyRunnableFour;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java 四种线程池newCachedThreadPool,newFixedThreadPool,newScheduledThreadPool,newSingleThreadExecutor - 星辰之力 - 博客园
 * https://www.cnblogs.com/zhujiabin/p/5404771.html
 * <p>
 * Java基础（九）：线程池中 submit()和 execute()方法区别_wanting1024的博客-CSDN博客_java线程池submit和execute区别
 * https://blog.csdn.net/qq_31473465/article/details/89647395
 * <p>
 * Java 线程池
 * <p>
 * Java通过Executors提供四种线程池，分别为：
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 */
public class twoFixed {
	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			cachedThreadPool.execute(new MyRunnableFour());
		}
	}
}
