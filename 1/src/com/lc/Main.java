package com.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

//			Scanner in = new Scanner(System.in);
//			while (in.hasNextInt()) {
//				int a = in.nextInt();
//				int b = in.nextInt();
//				System.out.println(a + b);
//
//
//	/*		零宽度空格-	它实际上是一个Unicode字符，是一个空格，关键是它没有宽度，因此我们一般肉眼看不到。
//		     但可以在vim下看到，上面的第二条语句中的2前面就有一个零宽度空格，放到vim中打开后你会发现是下面这样的语句：
//           System.out.println(Integer.parseInt("<feff>2"));*/
//
//				System.out.println(Integer.parseInt("1"));
//			//System.out.println(Integer.parseInt("2"));
//
//				//System.out.println((int)"2".charAt(0));
//
//		        System.exit(0);
//			}


		// 目标元素
		List<String> l = new ArrayList<>();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		l.add("5");
		l.add("6");
		// 待删除元素list
		List<String> l2 = new ArrayList<>();
		l2.add("1");
		l2.add("2");
		l2.add("3");
		l2.add("7");
		System.out.println("----------------------删除前---------------------------------------");
		System.out.println(l);
		System.out.println(l2);
		System.out.println("----------------------删除---------------------------------------");
		for (String s : l2) {
			if (l.contains(s)) {
				System.out.println("删除了" + s);
				l.remove(s);
			}else {
				System.out.println("无需删除不存在的元素了" + s);
			}
		}
		System.out.println("----------------------删除后---------------------------------------");
		System.out.println(l);
		System.out.println(l2);
	}
}

