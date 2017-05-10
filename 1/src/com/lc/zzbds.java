package com.lc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzbds {
	
	
	
	public static void main(String args[]) {
		String str = "ahlc@sina.cn";
		// 邮箱    在前面加个\ ! ------   \w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}
		String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());
	}

}
