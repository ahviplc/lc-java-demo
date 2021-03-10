package com.lc.offer.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * java线程池总结 - 小毛芋头 - 博客园
 * https://www.cnblogs.com/xiaomaoyvtou/p/13357137.html
 * <p>
 * 以阿里巴巴推荐的使用 ThreadPoolExecutor 构造函数自定义参数的方式来创建线程池
 * <p>
 * 可以看到我们上面的代码指定了：
 * <p>
 * corePoolSize: 核心线程数为 5。
 * maximumPoolSize ：最大线程数 10
 * keepAliveTime : 等待时间为 1L。
 * unit: 等待时间的单位为 TimeUnit.SECONDS。
 * workQueue：任务队列为 ArrayBlockingQueue，并且容量为 100;
 * handler:饱和策略为 CallerRunsPolicy。
 */
public class ThreadPoolExecutorDemo {

	private static final int CORE_POOL_SIZE = 5; // 核心线程数为 5
	private static final int MAX_POOL_SIZE = 10; // 最大线程数 10
	private static final int QUEUE_CAPACITY = 100; // workQueue：任务队列为 ArrayBlockingQueue，并且容量为 100
	private static final Long KEEP_ALIVE_TIME = 1L; // 等待时间为 1L

	public static void main(String[] args) {
		// 使用阿里巴巴推荐的创建线程池的方式
		// 通过ThreadPoolExecutor构造函数自定义参数创建
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				CORE_POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(QUEUE_CAPACITY),
				new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 1; i <= 20; i++) {
			// 创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
			Runnable worker = new MyThreadPoolRunnable("" + i);
			// 执行Runnable
			executor.execute(worker);
		}
		// 终止线程池
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}
}
