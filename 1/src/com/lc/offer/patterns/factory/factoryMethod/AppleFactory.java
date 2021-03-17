package com.lc.offer.patterns.factory.factoryMethod;

import com.lc.offer.patterns.factory.commons.IPhone;
import com.lc.offer.patterns.factory.commons.MiPhone;
import com.lc.offer.patterns.factory.commons.Phone;

/**
 * AppleFactory类：生产苹果手机的工厂（ConcreteFactory2）
 */
public class AppleFactory implements AbstractFactory {
	@Override
	public Phone makePhone() {
		return new IPhone();
	}
}
