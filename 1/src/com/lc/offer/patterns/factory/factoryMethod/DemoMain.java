package com.lc.offer.patterns.factory.factoryMethod;

public class DemoMain {
	public static void main(String[] arg) {
		AbstractFactory miFactory = new XiaoMiFactory();
		AbstractFactory appleFactory = new AppleFactory();
		miFactory.makePhone();            // make xiaomi phone!
		appleFactory.makePhone();        // make iphone!
	}
}
