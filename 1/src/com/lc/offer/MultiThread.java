package com.lc.offer;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 线程与进程相似，但线程是一个比进程更小的执行单位。一个进程在其执行的过程中可以产生多个线程。与进程不同的是同类的多个线程共享进程的堆和方法区资源，
 * 但每个线程有自己的程序计数器、虚拟机栈和本地方法栈，所以系统在产生一个线程，或是在各个线程之间作切换工作时，负担要比进程小得多，
 * 也正因为如此，线程也被称为轻量级进程。
 * <p>
 * Java 程序天生就是多线程程序，我们可以通过 JMX 来看一下一个普通的 Java 程序有哪些线程
 * <p>
 * 一个 Java 程序的运行是 main 线程和多个其他线程同时运行。
 *
 * JavaGuide - 2020最新Java并发基础常见面试题总结?id=_12-何为线程
 * https://snailclimb.gitee.io/javaguide/#/docs/java/multi-thread/2020最新Java并发基础常见面试题总结?id=_12-何为线程
 */
public class MultiThread {
	public static void main(String[] args) {
		// 获取 Java 线程管理 MXBean
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		// 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		// 遍历线程信息，仅打印线程 ID 和线程名称信息
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
		}
	}
}

//[6] Monitor Ctrl-Break
//[5] Attach Listener
//[4] Signal Dispatcher
//[3] Finalizer
//[2] Reference Handler
//[1] main

//[5] Attach Listener //添加事件
//[4] Signal Dispatcher // 分发处理给 JVM 信号的线程
//[3] Finalizer //调用对象 finalize 方法的线程
//[2] Reference Handler //清除 reference 线程
//[1] main //main 线程,程序入口
