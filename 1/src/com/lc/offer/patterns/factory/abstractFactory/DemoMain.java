package com.lc.offer.patterns.factory.abstractFactory;

public class DemoMain {
	public static void main(String[] args) {
		AbstractFactory miFactory = new XiaoMiFactory();
		AbstractFactory appleFactory = new AppleFactory();
		miFactory.makePhone();            // make xiaomi phone!
		miFactory.makePC();                // make xiaomi PC!
		appleFactory.makePhone();        // make iphone!
		appleFactory.makePC();            // make MAC!
	}
}
