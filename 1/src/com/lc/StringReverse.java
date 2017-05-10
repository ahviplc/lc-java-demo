package com.lc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;





public class StringReverse {
	
	 public static void main(String[] args) {
		  // 第一种，我最先能想得到的，利用下标倒序把字符取出来重组
		  System.out.println(reverse1("diyizhong"));
		 
		  // 第二种，我想了半天才想通，我发现我不适合编程了都
		  // 循环依然是从0到length-1，只不过是将先取出来的放在后取出来的后面
		  System.out.println(reverse2("dierzhong"));
		 
		  // 第三种，利用Collections类的反转list的方式，比较麻烦了
		  System.out.println(reverse3("disanzhong"));
		 
		  // 第四种，这种比第三种更加简洁一些，不过我自己倒是对Collections的反转方法记忆比较深刻
		  // 而对于StringBuilder或者StringBuffer的记忆不是很深刻，那么现在我记住了
		  System.out.println(reverse4("disizhong"));
		 
		  // 第五种，利用堆栈，所谓的先进后出
		  // 当然，首先你先从API中了解一下stack吧，它是对vector的扩展，通过push进行放入操作，然后通过pop方法进行弹出
		  // 我认为stack还是很有必要进行深入的研究，看看源码是怎么扩展的，我一定要掌握
		  System.out.println(reverse5("diwuzhong"));
		 
		  // 第六种，是完全copy别人的，我甚至都无法弄明白为什么？我一直觉得自己是一个逻辑痴呆者
		  // 我不知道自己是怎么经历了漫漫编程生涯的7年时光，包括大学哈，我一直很怀疑我自己能走多远，
		  // 因为对于第六种的方式，我是想清楚一会过一会就会马上忘记的那种，我很奇怪自己是靠什么活过来的
		  // 这一直都是我的痛点，我对各种排序算法有点过敏，我内心很厌恶这些排序算法，然而好像学习
		  // 编程的人必须要了解。否则不配做一个程序员，而我就属于不配的这种。
		 
		  // 这种做法无非就是先将第一个位置的字符替换成和在位置上对立的字符
		  // 其关键在于字符串的长度是奇数还是偶数的时候是否依然能够对称处理，显然，这种做法在处理1234的时候会循环两次
		  // 在处理123的时候也是两次，123的时候会把2作为对称的位置进行处理
		  // 但是最关键的还在于，其处理次数显然是最有效率的
		  // 犹记得当时一个面试的应聘者这样把题答出来了，我当时很惭愧，觉得自己无法面对自己的上司，因为我不曾想过还有这样的处理方法
		  // 这个方式还是很值得好好记住的
		  System.out.println(reverse6("diliuzhong"));
		  System.out.println(reverse6("1234"));
		  System.out.println(reverse6("123ds"));
		 }
		 
		 public static String reverse1(String old) {
		  String result = "";
		  for (int i = old.length() - 1; i >= 0; i--) {
		   result += String.valueOf(old.charAt(i));
		  }
		  return result;
		 }
		 
		 public static String reverse2(String old) {
		  String result = "";
		  for (int i = 0; i < old.length(); i++) {
		   result = old.charAt(i) + result;
		  }
		 
		  return result;
		 }
		 
		 
		 public static String reverse3(String old) {
			 
		  String result = "";
		  List<String> olds = Arrays.asList(old.split(""));
		  Collections.reverse(olds);
		 
		  for (String s : olds) {
		   result += s;
		  }
		  return result;
		 }
		 
		 public static String reverse4(String old) {
		  return new StringBuffer(old).reverse().toString();
		  // return new StringBuilder(old).reverse().toString();
		 }
		 
		 public static String reverse5(String old) {
		  char[] chars = old.toCharArray();
		 
		  Stack<Character>  oldStack = new Stack<Character>();
		  for (Character c : chars) {
		   oldStack.push(c);
		  }
		 
		  String result = "";
		 
		  while (!oldStack.empty()) {
		   result += oldStack.pop();
		  }
		 
		  return result;
		 }
		 
		 public static String reverse6(String old) {
		  char[] chars = old.toCharArray();
		  int n = chars.length - 1;
		 
		  for (int i = 0; i <= n / 2; i++) {
		   char temp = chars[i];
		 
		   chars[i] = chars[n - i];
		   chars[n - i] = temp;
		  }
		 
		  return new String(chars);
		 }

}
