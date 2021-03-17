package com.lc.offer.patterns.factory.abstractFactory;

import com.lc.offer.patterns.factory.commons.MiPC;
import com.lc.offer.patterns.factory.commons.MiPhone;
import com.lc.offer.patterns.factory.commons.PC;
import com.lc.offer.patterns.factory.commons.Phone;

/**
 * XiaoMiFactory类：生产小米手机的工厂（ConcreteFactory1）
 * XiaoMiFactory类：增加小米PC的制造（ConcreteFactory1）
 */
public class XiaoMiFactory implements AbstractFactory {
	@Override
	public Phone makePhone() {
		return new MiPhone();
	}

	@Override
	public PC makePC() {
		return new MiPC();
	}
}
