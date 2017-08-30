package com.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

	//统计一个字符串 每个元素（字符） 出现的 个数

public class shuzugeshutongji_2 {
	// 统计结果用，采用Character即char做键（Key）

 private Map<Character,Integer> countMap=new HashMap<Character, Integer>();

 public void countChar(String str){
	  
	  char[] chars=str.toCharArray(); //将字符串转换成字符char数组
	  // 循环，开始统计
	  for(char ch:chars){
		  //判断字符是否存在
		  
		  if(!countMap.containsKey(ch)){
			  //不存在，在map中加一个，并设置初始值为0
			  countMap.put(ch, 0);
			  
		  }
		  
		  //计数，将值+1
		  int count =countMap.get(ch);
		  countMap.put(ch, count+1);
	  }
	
	  //输出结果
	  Set<Character> keys  = countMap.keySet();
	  
	  for (Character ch:keys) {
		  
		  System.out.println("字符"+ch+"出现次数"+countMap.get(ch));
		
	}
	  
	  
  }
 
 
  public static void main(String[] args) {
	    
	  //测试方法
	  shuzugeshutongji_2 test=new shuzugeshutongji_2();
	  
	  test.countChar("Adfasadfadaere22");//不支持中文
	  	  
}
 
}
