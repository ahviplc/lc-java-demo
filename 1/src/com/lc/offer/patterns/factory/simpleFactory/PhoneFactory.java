package com.lc.offer.patterns.factory.simpleFactory;

import com.lc.offer.patterns.factory.commons.IPhone;
import com.lc.offer.patterns.factory.commons.MiPhone;
import com.lc.offer.patterns.factory.commons.Phone;

/**
 * PhoneFactory类：手机代工厂（Factory）
 */
public class PhoneFactory {
	public Phone makePhone(String phoneType) {
		if (phoneType.equalsIgnoreCase("MiPhone")) {
			return new MiPhone();
		} else if (phoneType.equalsIgnoreCase("iPhone")) {
			return new IPhone();
		}
		return null;
	}
}
