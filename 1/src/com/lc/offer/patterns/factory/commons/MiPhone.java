package com.lc.offer.patterns.factory.commons;

/**
 * MiPhone类：制造小米手机（Product1）
 */
public class MiPhone implements Phone {
	public MiPhone() {
		this.make();
	}
	@Override
	public void make() {
		System.out.println("make xiaomi phone!");
	}
}
