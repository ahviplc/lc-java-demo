package com.lc.offer;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 检查符号是否成对出现
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断该字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 比如 "()"、"()[]{}"、"{[]}" 都是有效字符串，而 "(]" 、"([)]" 则不是。
 * <p>
 * 这个问题实际是 Leetcode 的一道题目，我们可以利用栈 Stack 来解决这个问题。
 * <p>
 * 首先我们将括号间的对应规则存放在 Map 中，这一点应该毋容置疑；
 * 创建一个栈。遍历字符串，如果字符是左括号就直接加入stack中，否则将stack 的栈顶元素与这个括号做比较，如果不相等就直接返回 false。遍历结束，如果stack为空，返回 true。
 *
 * JavaGuide - 线性数据结构?id=_322-检查符号是否成对出现
 * https://snailclimb.gitee.io/javaguide/#/docs/dataStructures-algorithms/data-structure/线性数据结构?id=_322-检查符号是否成对出现
 */
public class checkBracketsIsValidWithStack {
	// isValid
	public static boolean isValid(String s) {
		// 括号之间的对应规则
		HashMap<Character, Character> mappings = new HashMap<>();
		mappings.put(')', '(');
		mappings.put('}', '{');
		mappings.put(']', '[');
		// 新建一个栈
		Stack<Character> stack = new Stack<>();
		// 输入的字符串转为char数组
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			// 判断此 是不是 右边 括号
			if (mappings.containsKey(chars[i])) {
				// 是右边符号
				// 拿出栈顶元素 和 这个右边符号为key 对应的 value 值 作比较 如果不相等 直接为 false
				System.out.println(stack);
				char topEl = stack.pop();
				System.out.println(stack);
//				System.out.println(String.valueOf(topEl));
//				System.out.println(String.valueOf(chars[i]));
//				System.out.println(String.valueOf(mappings.get(chars[i])));
//				System.out.println(String.valueOf(topEl).equals(String.valueOf(mappings.get(chars[i]))));
				if (topEl != mappings.get(chars[i])) {
					return false;
				}
			} else {
				// 是左边符号 直接入栈
				stack.push(chars[i]);
			}
		}
		return stack.isEmpty();
	}

	// 反转字符串
	// 将字符串中的每个字符先入栈再出栈就可以了。
	public static String ReverseStr(String in) {
		if (in == null && in.length() > 0) {
			return null;
		}
		// 新建立一个栈stack 存储字符串
		Stack<Character> stringStack = new Stack<>();
		char[] inChar = in.toCharArray();
		// 将其按照顺序入栈
		for (char c : inChar) {
			stringStack.push(c);
		}
		// 再将其 依次 出栈 即可得到 反转后的 字符
		// 新建个 StringBuilder
		StringBuilder sb = new StringBuilder();

		// peek pop
		// 相同点：大家都返回栈顶的值。
		//不同点：peek 不改变栈的值(不删除栈顶的值)，pop会把栈顶的值删除。
//		List<Character> subList = stringStack.subList(0, stringStack.size());
//		System.out.println(subList); // [a, b, c, d]
		// final 修饰 代表不可改变
		final int size_Alaywas = stringStack.size();
		// 一直pop 直到为空栈
		// 方法1
//		while (!stringStack.isEmpty()) {
//			sb.append(stringStack.pop().toString());
//		}

		// 方法2
		for (int i = 0; i < size_Alaywas; i++) {
			sb.append(stringStack.pop().toString());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
//		System.out.println(isValid("()"));
//		System.out.println(isValid("()[]{}"));
//		System.out.println(isValid("{[]}"));
//		System.out.println(isValid("([)]"));
		System.out.println(ReverseStr("abcd123456"));
	}
}


