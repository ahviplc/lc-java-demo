package com.lc.offer.patterns.factory.simpleFactory;

import com.lc.offer.patterns.factory.commons.IPhone;
import com.lc.offer.patterns.factory.commons.Phone;

public class DemoMain {
	public static void main(String[] args) {
		PhoneFactory factory = new PhoneFactory();
		Phone miPhone = factory.makePhone("MiPhone");            // make xiaomi phone!
		miPhone.make();
		IPhone iPhone = (IPhone) factory.makePhone("iPhone");    // make iphone!
		iPhone.make();
	}
}
