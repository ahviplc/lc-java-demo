package com.lc.offer.patterns.factory.commons;
/**
 * 抽象工厂模式(Abstract Factory)
 * 上面两种模式不管工厂怎么拆分抽象，都只是针对一类产品Phone（AbstractProduct），如果要生成另一种产品PC，应该怎么表示呢？
 * <p>
 * 最简单的方式是把2中介绍的工厂方法模式完全复制一份，不过这次生产的是PC。但同时也就意味着我们要完全复制和修改Phone生产管理的所有代码，显然这是一个笨办法，并不利于扩展和维护。
 * <p>
 * 抽象工厂模式通过在AbstarctFactory中增加创建产品的接口，并在具体子工厂中实现新加产品的创建，当然前提是子工厂支持生产该产品。否则继承的这个接口可以什么也不干。
 */

/**
 * PC类：定义PC产品的接口(AbstractPC)
 */
public interface PC {
	void make();
}
