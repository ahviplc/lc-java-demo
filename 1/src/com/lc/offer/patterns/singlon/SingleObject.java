package com.lc.offer.patterns.singlon;

public class SingleObject {
	private static SingleObject instance = null;

	private SingleObject (){}

	public static SingleObject getInstance() {
		if (instance == null) {
			instance = new SingleObject();
		}
		return instance;
	}
}

class mainApp{
	public static void main(String[] args) {
		//获取唯一可用的对象
		SingleObject object = SingleObject.getInstance();

		//获取唯一可用的对象
		SingleObject object2 = SingleObject.getInstance();

		System.out.println(object == object2);
	}
}
