package com.lc.offer;

/**
 * 线程死锁描述的是这样一种情况：多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。由于线程被无限期地阻塞，因此程序不可能正常终止。
 * <p>
 * 如下图所示，线程 A 持有资源 2，线程 B 持有资源 1，他们同时都想申请对方的资源，所以这两个线程就会互相等待而进入死锁状态。
 * 下面通过一个例子来说明线程死锁,代码模拟了上图的死锁的情况 (代码来源于《并发编程之美》)
 * <p>
 * JavaGuide
 * https://snailclimb.gitee.io/javaguide/#/docs/java/multi-thread/2020最新Java并发基础常见面试题总结?id=_8-什么是线程死锁如何避免死锁
 * <p>
 * 学过操作系统的朋友都知道产生死锁必须具备以下四个条件：
 * <p>
 * 互斥条件：该资源任意一个时刻只由一个线程占用。
 * 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 * 不剥夺条件:线程已获得的资源在未使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
 * 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 * <p>
 * 如何避免线程死锁?
 * 我上面说了产生死锁的四个必要条件，为了避免死锁，我们只要破坏产生死锁的四个条件中的其中一个就可以了。现在我们来挨个分析一下：
 * <p>
 * 破坏互斥条件 ：这个条件我们没有办法破坏，因为我们用锁本来就是想让他们互斥的（临界资源需要互斥访问）。
 * 破坏请求与保持条件 ：一次性申请所有的资源。
 * 破坏不剥夺条件 ：占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
 * 破坏循环等待条件 ：靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放。破坏循环等待条件。
 */
public class DeadLockDemo {
	private static Object resource1 = new Object();//资源 1
	private static Object resource2 = new Object();//资源 2

	public static void main(String[] args) {
		new Thread(() -> {
			synchronized (resource1) {
				System.out.println(Thread.currentThread() + "get resource1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread() + "waiting get resource2");
				synchronized (resource2) {
					System.out.println(Thread.currentThread() + "get resource2");
				}
			}
		}, "线程 1").start();

		new Thread(() -> {
			synchronized (resource2) {
				System.out.println(Thread.currentThread() + "get resource2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread() + "waiting get resource1");
				synchronized (resource1) {
					System.out.println(Thread.currentThread() + "get resource1");
				}
			}
		}, "线程 2").start();
	}
}



