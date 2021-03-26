package com.lc.offer.callback;

import static java.lang.System.out;
import static java.lang.System.err;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 在Java中如何编写回调函数，以及回调函数的简单应用 - osc_wo67p5c1的个人空间 - OSCHINA - 中文开源技术交流社区
 * https://my.oschina.net/u/4407434/blog/4043242
 * <p>
 * Http客户端工具类-HttpUtil
 * https://hutool.cn/docs/#/http/Http客户端工具类-HttpUtil
 * <p>
 * public static long downloadFile(String url, File destFile, StreamProgress streamProgress)
 * <p>
 * 就有回调函数,可参考
 */
public class CallbackExample {
	// 定义 Responser 接口
	private interface Responser {
		void onSuccess(String data);

		void onFailed(String prompt);
	}

	private static String doSomething() {
		try {
			out.println("doSomething-开始操作...");
			Thread.sleep(5000); //模拟耗时操作（如网络请求），操作正常返回"Success"，"Success"表示有效的数据
			return "Success";
		} catch (InterruptedException ex) {
			Logger.getLogger(CallbackExample.class.getName()).log(Level.SEVERE, null, ex);
			//操作出现问题返回"Failed"，"Failed"包含错误提示，如错误码等
			return "Failed";
		}
	}

	private static void doSomethingInWithCallBack(Responser responser) {
		try {
			out.println("doSomethingInWithCallBack-开始操作...");
			Thread.sleep(5000); //模拟耗时操作（如网络请求），操作正常返回"Success"，"Success"表示有效的数据
			// return "Success";
			responser.onSuccess("Success");
		} catch (InterruptedException ex) {
			Logger.getLogger(CallbackExample.class.getName()).log(Level.SEVERE, null, ex);
			// 操作出现问题返回"Failed"，"Failed"包含错误提示，如错误码等
			// return "Failed";
			responser.onFailed("Failed");
		}
	}

	//使用回调函数完成操作
	private static void callbackDoSomething(Responser responser) {
		try {
			out.println("callbackDoSomething-开始操作...");
			Thread.sleep(5000);
			responser.onSuccess("Success");
		} catch (InterruptedException ex) {
			Logger.getLogger(CallbackExample.class.getName()).log(Level.SEVERE, null, ex);
			responser.onFailed("Failed");
		}
	}

	public static void main(String[] args) {
		out.println("正常模式 ------ " + doSomething());
		callbackDoSomething(new Responser() {
			@Override
			public void onSuccess(String data) {
				out.println("回调模式 ------ " + data);
			}

			@Override
			public void onFailed(String prompt) {
				err.println("错误提示：" + prompt);
			}
		});

		out.println("正常模式2 ------doSomethingInWithCallBack()------ 执行中 ------ ");
		doSomethingInWithCallBack(new Responser() {
			@Override
			public void onSuccess(String data) {
				out.println("回调模式2 ------ " + data);
			}

			@Override
			public void onFailed(String prompt) {
				err.println("错误提示2：" + prompt);
			}
		});
	}
}

//	doSomething-开始操作...
//	正常模式 ------ Success
//	callbackDoSomething-开始操作...
//	回调模式 ------ Success
//	正常模式2 ------doSomethingInWithCallBack()------ 执行中 ------
//	doSomethingInWithCallBack-开始操作...
//	回调模式2 ------ Success
