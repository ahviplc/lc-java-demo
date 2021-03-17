package com.lc.offer.patterns.factory.abstractFactory;

import com.lc.offer.patterns.factory.commons.IPhone;
import com.lc.offer.patterns.factory.commons.MAC;
import com.lc.offer.patterns.factory.commons.PC;
import com.lc.offer.patterns.factory.commons.Phone;

/**
 * AppleFactory类：生产苹果手机的工厂（ConcreteFactory2）
 * AppleFactory类：增加苹果PC的制造（ConcreteFactory2）
 */
public class AppleFactory implements AbstractFactory {
	@Override
	public Phone makePhone() {
		return new IPhone();
	}

	@Override
	public PC makePC() {
		return new MAC();
	}
}
