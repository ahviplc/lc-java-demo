package com.lc;

import java.util.HashMap;
import java.util.Map;

public class shuzugeshutongji {
	//统计一个字符数组  每个元素（ 字符） 出现的 个数
	public static void main(String[] args){
		String[] array = {"a","b","c","a","b","a"};
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		for(int i = 0 ; i<array.length;i++){
			String temp = array[i];
			Integer count = map.get(temp);
			if(null == count){
				map.put(temp, 1);
			}else{
				map.put(temp, map.get(temp)+1);
			}
		}
		for(String key : map.keySet()){
			System.out.println(key+":"+map.get(key));
		}
	}

}
