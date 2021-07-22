package com.threadPoolFour;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import com.threadPoolFour.my.MyRunnableFour;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
 * Java 线程池
 * <p>
 * Java通过Executors提供四种线程池，分别为：
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 */
public class threeScheduled {
	public static void main(String[] args) throws InterruptedException {
		// 核心线程数 1个
		ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		// 核心线程数 自定义 6个
		ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(6);
		Console.log("{} => {}", DateUtil.now(), Thread.currentThread().getName());
		for (int i = 0; i < 10; i++) {
			// 核心线程数 1个
			singleThreadScheduledExecutor.execute(new MyRunnableFour());
			// 核心线程数 自定义 6个
			// scheduledThreadPool.execute(new MyRunnableFour());
		}

		// 执行完毕
		// 关闭线程池
		// isShutdown
		// 如果此执行程序已关闭，则返回true 。
		// 返回：如果此执行程序已关闭，则为true
		Console.log("{} isShutdown =>前 {}", DateUtil.now(), singleThreadScheduledExecutor.isShutdown());
		Console.log("{} isTerminated =>前 {}", DateUtil.now(), singleThreadScheduledExecutor.isTerminated());
		// 只是告诉 我想结束了 但是 要执行的任务会继续执行
		// shutdown()
		// 启动有序关闭，其中执行先前提交的任务，但不会接受新任务。 如果已经关闭，调用没有额外的效果。
		// 此方法不等待先前提交的任务完成执行。 使用awaitTermination来做到这一点。
		// 抛出：SecurityException – 如果安全管理器存在并且关闭此 ExecutorService 可能会操纵调用者不允许修改的线程，因为它不持有RuntimePermission ("modifyThread") ，或者安全管理器的checkAccess方法拒绝访问。
		singleThreadScheduledExecutor.shutdown();
		Console.log("{} isShutdown =>后 {}", DateUtil.now(), singleThreadScheduledExecutor.isShutdown());
		// isTerminated 会在任务都完全处理完成了 才会为true
		// 如果关闭后所有任务都已完成，则返回true 。 请注意，除非先调用shutdown或shutdownNow ，否则isTerminated永远不会为true 。
		// 返回：如果关闭后所有任务都已完成，则为true
		Console.log("{} isTerminated =>后 {}", DateUtil.now(), singleThreadScheduledExecutor.isTerminated());

		Thread.sleep(3000);

		//
		// Java中使用isTerminated()函数判断线程池是否结束
		// 当需要用到isTerminated()函数判断线程池中的所有线程是否执行完毕时候，不能直接使用该函数，必须在shutdown()方法关闭线程池之后才能使用，否则isTerminated()永不为TRUE，线程将一直阻塞在该判断的地方，导致程序最终崩溃
		Console.log("{} isTerminated =>3秒后 {}", DateUtil.now(), singleThreadScheduledExecutor.isTerminated());
		// 关闭线程池 立马
		// singleThreadScheduledExecutor.shutdownNow();
	}
}
