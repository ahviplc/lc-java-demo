package com.lc;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/*
 * 统计abacbacdadbc中的每个字母出现的次数，输出格式是：a(4)b(3)c(3)d(2)
 * 
 * 选中TreeMap的原因是:key不重复且按顺序排序取出
 * 
 * 思路：
 * 1.将字符串abacbacdadbc转换成字符数组
 * 2.分别取字符，与TreeMap中的key进行比较
 *   2.1 如果TreeMap中存在对应的字符，则取出并自增，再存入TreeMap
 *   2.2 如果TreeMap中不存在对应的字符，则直接存入该字符，value=1
 */

public class A3B1C2_TreeMapTest {

	public static void main(String[] args) {

		TreeMap<String, Integer> tm=new TreeMap<>();
		String string="abacbacdadbc";
		char[] ch=string.toCharArray();
		
		for(char r :ch){
			//tm.put(String.valueOf(r), 1);
			//System.out.println(r);	
			if(compareKey(r, tm))
			{
				int i=tm.get(String.valueOf(r));
				i++;
				tm.put(String.valueOf(r), i);
			}else {
				tm.put(String.valueOf(r), 1);
			}
		}
		System.out.println(tm);	
		Set<Map.Entry<String, Integer>> entrySet=tm.entrySet();
		Iterator<Map.Entry<String, Integer>> iterator=entrySet.iterator();
		while(iterator.hasNext()){
			Map.Entry<String, Integer> me=iterator.next();
			String key=me.getKey();
			int value=me.getValue();
			System.out.print(key+"("+value+")");			
		}
		
	}
	public static boolean compareKey(char c,TreeMap<String, Integer> treeMap){
		if (treeMap.containsKey(String.valueOf(c))) {
			return true;
		}
		return false;
	}

}