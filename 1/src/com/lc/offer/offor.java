package com.lc.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试 为了拿offor 小例子
 * 2021年3月7日10:34:40
 */
class User {

	// 实例代码块语法执行时机:
    // main方法的执行不会触发实例代码块;
    // 在new对象的时候才触发实例代码块执行, 且在对象产生之前就执行了.
    // 每new一个对象都会触发执行一次.
	{
		System.out.println("User 实例代码块 1");
	}

	{
		System.out.println("User 实例代码块 2");
	}

	{
		System.out.println("User 实例代码块 3");
	}

	// 静态代码块在类加载时执行, 并且只执行一次
	// 静态代码块可以有多个 按照顺序执行
	static {
		System.out.println("静态代码块 static 1");
	}

	static {
		System.out.println("静态代码块 static 2");
	}

	static String s = "static 3";
	static char result = s.charAt(0);

	static {
		System.out.println(result + " from 静态代码块 static 3");
	}

	static {
		System.out.println("静态代码块 static 4");
	}

	public final String name = "123";

	public static final String hobby = "codeing...";

	int deaAge; // 默认值为0

	public final Integer age = 18;

	public void say() {
		System.out.println("nihao");
//		System.out.println(this.deaAge); // 0
//		System.out.println(User.hobby); // codeing...
		//System.out.println(User.name); // Error:(21, 40) java: 无法从静态上下文中引用非静态 变量 name
		//this.name = "321"; // Cannot assign a value to final variable 'name'
	}

	static void say2() {
		System.out.println("nihao2");
	}

	final void say3() {
		System.out.println("nihao3");
	}
}

class vipUser extends User {

	{
		System.out.println("vipUser 实例代码块 1");
	}

	{
		System.out.println("vipUser 实例代码块 2");
	}

	{
		System.out.println("vipUser 实例代码块 3");
	}

	static {
		System.out.println("静态代码块 static 5");
	}

	@Override
	public void say() {
		System.out.println("nihao VIP");
	}

	static void say2() {
		System.out.println("nihao2 VIP");
	}

//	@Override
//	final void say3() { // 'say3()' cannot override 'say3()' in 'com.lc.offer.User'; overridden method is final
//		System.out.println("nihao3");
//	}


}

public class offor {
	public static void main(String a[]) {
//		System.out.println(1);
//		System.out.printf("我是%s", "123");

		User.say2(); // nihao2
		vipUser.say2(); // nihao2 VIP

		User user = new User();
//		user.say(); // nihao
//		user.say2(); // nihao2
//		User.say2(); // nihao2
//		user.say3(); // nihao3
//
		User vuser = new vipUser();
//		vuser.say(); // nihao VIP
//		vuser.say2(); // nihao2
//		vipUser.say2(); // nihao2 VIP
//		vuser.say3(); // nihao3
//
//		List<String> s = new ArrayList<>();
	}
}
