package com.threadPoolFour.my;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

public class MyRunnableFour implements Runnable {
	@Override
	public void run() {
		Console.log("{} => {}", DateUtil.now(), Thread.currentThread().getName());
	}
}
