package com.lc;

/*
 * 使用两种方法把这个字符串中的单词首字母转为大写：String str = “hello java and Android!”；
 */
public class StringCaoZuoUp {
	
	public static void main(String[] args) {  
        
        String str = "hello java and android!~LC";  
          
        //方法一  
        char[] chars = str.toCharArray();//把字符串转换为字符数组        
        chars[0] = (char) ((int)chars[0]-32);//先把第一个单词的首字母转大写  
          
        for (int i = 0; i < chars.length-1; i++) {  
            if (' '==chars[i]) {                  
                chars[i+1] = (char)(chars[i+1] - 32);  
            }  
        }  
          
        String str1 = new String(chars); 
        System.out.println("--1--"+str1);  
        System.out.println("--1.1--"+chars.length+"----"+chars.hashCode()+"----"+chars[0]);  
              
    //////////////////////////////////////////  
      
        //方法二  
        StringBuilder sb= new StringBuilder();  
        String[] strs = str.split(" ");//使用split方法把字符串按“ ”空格字符串分割成字符串数组  
        for (int i = 0; i < strs.length; i++) {  
            String s = strs[i].substring(0,1).toUpperCase()  
                    +strs[i].substring(1, strs[i].length());  
            System.out.println("--2.1--"+s);  
            sb.append(s+" ");//使用StringBuilder进行拼接  
              
        }  
        String str3 = sb.toString();  
        System.out.println("--2--"+str3);  
          
    }  

}
