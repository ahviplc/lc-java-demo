package com.lc.offer.patterns.factory.commons;

/**
 * IPhone类：制造苹果手机（Product2）
 */
public class IPhone implements Phone {
	public IPhone() {
		this.make();
	}
	@Override
	public void make() {
		System.out.println("make iphone!");
	}
}
