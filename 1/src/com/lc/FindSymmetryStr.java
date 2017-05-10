package com.lc;
/** 
 * 题目要求：

用Java设计一个程序,实现一个字符串的对称个数,如字符串"effeghg",有"ff","effe","ghg"这三个对称字符,所以返回3.

 

我实现的思路就是遍历这个字符串，

先选定头位置为第一个字符，然后从最后向前遍历这个字符串，

头尾两个字符相同，则取中间字符串，进行递归。

递归结束后得到结果，

继续将头向后推1位，然后再从字符串最后向前遍历，

如此循环，当尾等于头时，退出最外层循环，输出结果。
 * 找出字符串中对称的子字符串的个数 
 * @param orgStr 
 * @return 
 */ 
public class FindSymmetryStr {
	 public static int findSymmetryStr(String orgStr) { 
	        //结果初始化 
	        int count = 0; 

	        //当输入字符串不为null且长度大于1时进行查找,否则直接返回0 
	        if (orgStr != null && orgStr.length() > 1) { 
	            //得到输入字符串的长度 
	            int size = orgStr.length(); 
	            //字符串的头字符索引 
	            int head; 
	            //字符串从后向前遍历时的"尾"字符索引,即当前字符索引 
	            int current; 
	            //字符串的头字符 
	            char hStr; 
	            //字符串从后向前遍历时的"尾"字符 
	            char cStr; 

	            //从前开始遍历字符串 
	            for (head = 0; head < size; head++) { 
	                //取得头字符 
	                hStr = orgStr.charAt(head); 
	                //指向输入字符串的最后 
	                current = size - 1; 
	                //当尾字符索引等于头字符索引时退出循环 
	                while (current > head) { 
	                    //取得尾字符 
	                    cStr = orgStr.charAt(current); 
	                    //如果头尾字符相等,则继续判断 
	                    if (hStr == cStr) { 
	                        //取出头尾中间的子字符串,对其进行分析 
	                        String newStr = orgStr.substring(head + 1, current); 
	                        //如果此子字符串的长度大于1,则进行递归 
	                        if (newStr.length() > 1) 
	                            //递归得到此子字符串中对称的字符串个数 
	                            count += findSymmetryStr(newStr); 
	                        //如果此子字符串只有1个或0个字符,则表明原头尾字符和此单个字符组成对称字符串 
	                        else 
	                            count++; 
	                        //将尾字符索引向前推1位 
	                        current--; 

	                    } 
	                    //如果头尾字符不相等,则将尾字符索引向前推1位 
	                    else { 
	                        current--; 
	                    } 
	                } 
	            } 
	        } 

	        return count; 
	    } 

	    //测试程序 
	    public static void main(String args[]) { 
	        int count = findSymmetryStr("effeghg");// effeghg,cddcbcbeffeghg
	        System.out.println("symmetry string count is : " + count); 
	    } 
}
