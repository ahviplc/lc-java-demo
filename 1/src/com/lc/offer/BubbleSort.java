package com.lc.offer;

/**
 * Java基础(冒泡排序)_可爱的糖糖-CSDN博客_java冒泡排序
 * https://blog.csdn.net/qq_41679818/article/details/90296399
 */
public class BubbleSort {
	// 冒泡排序算法
	public static int[] sortMe(int[] ints) {
		// int[] numbers = new int[]{1, 5, 8, 2, 3, 9, 4};
		int[] numbers = ints;
		// 需进行length-1次冒泡
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = 0; j < numbers.length - 1 - i; j++) {
				if (numbers[j] > numbers[j + 1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}
			}
		}
		return numbers;
	}

	public static void main(String[] args) {
		// write your code here
		// System.out.println("hello world");
		// 创建有初始值的int数组
		int[] numbers = new int[]{1, 5, 8, 2, 3, 9, 4};

		// 创建空的int数组 指定大小 3
		int[] numbers2 = new int[3];
		numbers2[0] = 1;
		numbers2[1] = 3;
		numbers2[2] = 2;

		int[] ints = sortMe(numbers);
		for (int n : ints) {
			System.out.println(n);
		}

		for (int n : numbers2) {
			System.out.println(n);
		}
	}
}

