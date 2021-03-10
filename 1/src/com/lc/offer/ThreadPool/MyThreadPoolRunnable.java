package com.lc.offer.ThreadPool;

import java.util.Date;

/**
 * 这是一个简单的Runnable类，需要大约5秒钟来执行其任务。
 *
 * @author lc
 */
public class MyThreadPoolRunnable implements Runnable {

	private String command;

	public MyThreadPoolRunnable(String s) {
		this.command = s;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
		System.out.println(this.toString());
		// 睡5秒
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
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
