package com.lc.offer.patterns.factory.factoryMethod;

import com.lc.offer.patterns.factory.commons.MiPhone;
import com.lc.offer.patterns.factory.commons.Phone;

/**
 * XiaoMiFactory类：生产小米手机的工厂（ConcreteFactory1）
 */
public class XiaoMiFactory implements AbstractFactory {
	@Override
	public Phone makePhone() {
		return new MiPhone();
	}
}
