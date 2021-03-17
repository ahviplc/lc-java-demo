package com.lc.offer.patterns.factory.commons;

/**
 * 设计模式之工厂模式（factory pattern） - alpha_panda - 博客园
 * https://www.cnblogs.com/yssjun/p/11102162.html
 */

/**
 * 简单工厂模式 - 该模式对对象创建管理方式最为简单，因为其仅仅简单的对不同类对象的创建进行了一层薄薄的封装。
 * 该模式通过向工厂传递类型来指定要创建的对象.
 * <p>
 * 手机标准规范类(AbstractProduct)
 */
public interface Phone {
	void make();
}
