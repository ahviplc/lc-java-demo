package com.lc.offer.callback;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.err;

import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 在Java中如何编写回调函数，以及回调函数的简单应用 - osc_wo67p5c1的个人空间 - OSCHINA - 中文开源技术交流社区
 * https://my.oschina.net/u/4407434/blog/4043242
 */
public class CallbackExample2 {
	// 定义 HttpResponser 接口
	private interface HttpResponser {
		void onSuccess(String response);

		void onError(String msg);
	}

	private static String httpRequest() {
		try {
			String urlStr = "http://api.eyekey.com/face/Check/checking?app_id=f89ae61fd63d4a63842277e9144a6bd2&app_key=af1cd33549c54b27ae24aeb041865da2&url=http://f.hiphotos.baidu.com/baike/pic/item/562c11dfa9ec8a1366377e5efe03918fa0ecc05a.jpg";
			URLConnection c = new URL(urlStr).openConnection();
			BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String line;
			StringBuilder strBuf = new StringBuilder();
			while ((line = inputFromServer.readLine()) != null) {
				strBuf.append(line);
			}
			return strBuf.toString();
		} catch (IOException ex) {
			Logger.getLogger(CallbackExample2.class.getName()).log(Level.SEVERE, null, ex);
			return ex.toString();
		}
	}

	private static void callbackHttpRequest(HttpResponser httpResponser) {
		try {
			String urlStr = "http://api.eyekey.com/face/Check/checking?app_id=f89ae61fd63d4a63842277e9144a6bd2&app_key=af1cd33549c54b27ae24aeb041865da2&url=http://f.hiphotos.baidu.com/baike/pic/item/562c11dfa9ec8a1366377e5efe03918fa0ecc05a.jpg";
			URLConnection c = new URL(urlStr).openConnection();
			BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String line;
			StringBuilder strBuf = new StringBuilder();
			while ((line = inputFromServer.readLine()) != null) {
				strBuf.append(line);
			}
			httpResponser.onSuccess(strBuf.toString());
		} catch (IOException ex) {
			Logger.getLogger(CallbackExample2.class.getName()).log(Level.SEVERE, null, ex);
			httpResponser.onError(ex.toString());
		}
	}

	public static void main(String[] args) {
		out.println(httpRequest());
		callbackHttpRequest(new HttpResponser() {
			@Override
			public void onSuccess(String response) {
				err.println(response);
			}

			@Override
			public void onError(String msg) {
				err.println(msg);
			}
		});
	}
}
