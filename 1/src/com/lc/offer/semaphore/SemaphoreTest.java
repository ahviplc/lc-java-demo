package com.lc.offer.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	//定义线程数量;
	private static final int THREAD_COUNT = 2;
	//初始化信号量为0，默认是非公平锁
	private static Semaphore semaphore = new Semaphore(0,false);
	private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

	public static void main(String[] args) throws InterruptedException {
		for (int i=0; i<THREAD_COUNT; i++) {
			executorService.submit(()->{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"执行");
				semaphore.release();
			});
		}
		//获取2个信号量
		semaphore.acquire(2);
		System.out.println("主线程执行完毕");
		executorService.shutdown();
	}
}
