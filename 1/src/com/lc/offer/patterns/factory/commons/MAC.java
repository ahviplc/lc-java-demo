package com.lc.offer.patterns.factory.commons;

/**
 * MAC类：定义苹果电脑产品(MAC)
 */
public class MAC implements PC {
	public MAC() {
		this.make();
	}
	@Override
	public void make() {
		System.out.println("make MAC!");
	}
}
