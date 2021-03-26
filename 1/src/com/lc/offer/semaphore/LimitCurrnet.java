package com.lc.offer.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量计数器。和CountDownLatch，CyclicBarrier类似，是多线程协作的工具类，
 * 相对于join，wait，notify方法使用起来简单高效
 * <p>
 * Semaphore实战-叫练的博客-51CTO博客
 * https://blog.51cto.com/14883474/2647317
 */
public class LimitCurrnet {

	static class myRunnableLimitCurrnet implements Runnable {

		@Override
		public void run() {
			System.out.println("ThreadName -> " + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {
		//定义20个线程，每次最多只能执行5个线程;
		Semaphore semaphore = new Semaphore(5);
		for (int i = 0; i < 20; i++) {
			new Thread(() -> {
				try {
					//获取凭证
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "登录成功");
					Thread.sleep(2000);
					//释放凭证
					semaphore.release();
					System.out.println(Thread.currentThread().getName() + "用户退出");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}

		new Thread(new myRunnableLimitCurrnet(), "我是myRunnableLimitCurrnet啊").start();
	}
}
