package com.lc.offer;

/**
 * Java多线程看这一篇就足够了（吐血超详细总结） - Java团长 - 博客园
 * https://www.cnblogs.com/java1024/archive/2019/11/28/11950129.html
 * <p>
 * Java中线程实现的方式
 * 在 Java 中实现多线程有两种手段，一种是继承 Thread 类，另一种就是实现 Runnable 接口。下面我们就分别来介绍这两种方式的使用。
 * <p>
 * 实现 Runnable 接口
 */
class MyThread implements Runnable { // 实现Runnable接口，作为线程的实现类
	private String name;       // 表示线程的名称

	public MyThread(String name) {
		this.name = name;      // 通过构造方法配置name属性
	}

	public void run() {  // 覆写run()方法，作为线程 的操作主体
		for (int i = 0; i < 10; i++) {
			System.out.println(name + "运行，i = " + i);
		}
	}
};

public class RunnableDemo01 {
	public static void main(String args[]) {
		MyThread mt1 = new MyThread("线程A - 实现 Runnable 接口 ");    // 实例化对象
		MyThread mt2 = new MyThread("线程B - 实现 Runnable 接口");    // 实例化对象
		Thread t1 = new Thread(mt1);       // 实例化Thread类对象
		Thread t2 = new Thread(mt2);       // 实例化Thread类对象
		t1.start();    // 启动多线程
		t2.start();    // 启动多线程
	}
}

// Java多线程学习——join方法的使用 - 剑冢、 - 博客园
// https://www.cnblogs.com/chiweiming/p/11095682.html
// 使用了 join()
// join在线程里面意味着“插队”，哪个线程调用join代表哪个线程插队先执行——但是插谁的队是有讲究了，
// 不是说你可以插到队头去做第一个吃螃蟹的人，而是插到在当前运行线程的前面，比如系统目前运行线程A，
// 在线程A里面调用了线程B.join方法，则接下来线程B会抢先在线程A面前执行，等到线程B全部执行完后才继续执行线程A
class RunnableDemo02 {
	public static void main(String args[]) throws InterruptedException {
		MyThread mt1 = new MyThread("线程A - 实现 Runnable 接口 ");    // 实例化对象
		MyThread mt2 = new MyThread("线程B - 实现 Runnable 接口 ");    // 实例化对象
		Thread t1 = new Thread(mt1);       // 实例化Thread类对象
		Thread t2 = new Thread(mt2);       // 实例化Thread类对象
		t1.start();    // 启动多线程
		t2.start();    // 启动多线程

		t1.join();
		t2.join();//让线程2先执行完主线程才能继续执行
		System.out.println("------------------t1 t2线程到此 执行完毕-------------------");
		System.out.println("------------------主线程到此 进行开始下面执行-------------------");

		for (int i = 0; i < 20; i++) {
			System.out.println("主线程" + i);
		}
	}
}

// 取得和设置线程的名称
class MyThread3 implements Runnable { //实现Runnable接口
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + "运行, i=" + i);  //取得当前线程的名称
		}
	}
}

// MyThread3 main
class ThreadDemo03 {
	public static void main(String args[]) {
		MyThread3 my = new MyThread3();  //定义Runnable子类对象
		new Thread(my).start();    //系统自动设置线程名称
		new Thread(my, "线程A").start();  //手工设置线程名称
	}
}

// 其他 请看上面博客
