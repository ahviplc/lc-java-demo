package com.lc;

import java.util.HashMap;
import java.util.Map;

public class int_shuzugeshutongji {
	
	//统计一个 int 数组 每个元素（数字） 出现的 个数
	
	public static void main(String[] args){
		int[] array = {1,2,1,3,4,2,2,5};
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
		for(int i = 0 ; i<array.length;i++){
			Integer temp = array[i];
			Integer count = map.get(temp);
			if(null == count){
				map.put(temp, 1);
			}else{
				map.put(temp, map.get(temp)+1);
			}
		}
		for(Integer key : map.keySet()){
			System.out.println(key+":"+map.get(key));
		}
	}

}
