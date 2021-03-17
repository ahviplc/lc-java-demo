package com.lc.offer.patterns.factory.commons;

/**
 * MiPC类：定义小米电脑产品(MIPC)
 */
public class MiPC implements PC{
	public MiPC() {
		this.make();
	}
	@Override
	public void make() {
		System.out.println("make xiaomi PC!");
	}
}
