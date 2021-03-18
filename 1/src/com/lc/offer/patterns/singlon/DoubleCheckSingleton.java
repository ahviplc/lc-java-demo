package com.lc.offer.patterns.singlon;

/**
 * 双重校验锁实现对象单例（线程安全）
 * https://snailclimb.gitee.io/javaguide/#/docs/java/multi-thread//2020最新Java并发进阶常见面试题总结?id=_12-说说自己是怎么使用-synchronized-关键字
 */
public class DoubleCheckSingleton {
	private static volatile DoubleCheckSingleton uniqueInstance;

	private DoubleCheckSingleton() {
	}

	public static DoubleCheckSingleton getUniqueInstance() {
		//先判断对象是否已经实例过，没有实例化过才进入加锁代码
		if (uniqueInstance == null) {
			//类对象加锁
			synchronized (DoubleCheckSingleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new DoubleCheckSingleton();
				}
			}
		}
		return uniqueInstance;
	}
}
