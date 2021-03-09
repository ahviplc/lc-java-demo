package com.lc.offer;

/**
 * 单例模式的五种写法_absolute_chen的博客-CSDN博客_单例模式
 * https://blog.csdn.net/absolute_chen/article/details/93380566
 * <p>
 * 单例模式 懒汉式
 */
public class singletonClass {
	public static singletonClass instance;

	// 无参构造器私有化
	private singletonClass() {
	}

	public static singletonClass getInstance() {
		if (null == instance) {
			instance = new singletonClass();
		}
		return instance;
	}
}

class testMain {
	public static void main(String[] args) {
		// 因为无参构造器私有化了 所以下面写法不可
		// singletonClass singletonClass = new singletonClass();

		singletonClass instance = singletonClass.getInstance();
		singletonClass instance2 = singletonClass.getInstance();

		// 如果没有重写 equals() 使用它就相当于使用 ==
		System.out.println(instance.equals(instance2)); // true
		System.out.println((instance == instance2)); // true
		// 代表是同一个对象 也就是无论 getInstance() 多少 也都是同一个对象 也就实现了 单例模式

		System.out.println(instance.hashCode()); // 1067040082
		System.out.println(instance2.hashCode()); // 1067040082
		System.out.println(System.identityHashCode(instance)); // 1067040082
		System.out.println(System.identityHashCode(instance2)); // 1067040082
		// 在Java一般使用HashCode来代表对象的地址，但是两个相同的对象就不行了，两个相同的对象的hashcode是相同的
        // 由此可知，要想获取对象的内存地址应使用System.identityHashCode()方法
	}
}
