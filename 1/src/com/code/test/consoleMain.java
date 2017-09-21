package com.code.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
/*
 * java执行windows下cmd命令的方法
  @LC 2017年9月21日11:59:55
 */
public class consoleMain {
	
	
	//通用方法，只需要将要执行的命令当做实参传入即可
	public static void exeCmd(String commandStr) {  
	        BufferedReader br = null;  
	        try {  
	            Process p = Runtime.getRuntime().exec(commandStr);  
	            br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("gbk")));  
	            String line = null;  
	            StringBuilder sb = new StringBuilder();  
	            while ((line = br.readLine()) != null) {  
	                sb.append(line + "\n");  
	            }

	            String newstr=sb.toString();
	         //没有用 这句  newstr = new String(newstr.getBytes("gbk"),"utf-8");
	            
	            System.out.println(newstr);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }   
	        finally  
	        {  
	            if (br != null)  
	            {  
	                try {  
	                    br.close();  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	    }  
	
	/*
	 *在这个 建立个 F:\JavaCode\JavacTest.java  ！
	 *
	 *javac-这个可以使用
	 *
	 *java-这个没整好
	 *
	 *
	 *        public class JavacTest {
                   public static void main(String []args) {
                      System.out.println("Hello World-LC");
                                                           }
                                     }
	 *
	 */
	public static void main(String[] args) {
	consoleMain.exeCmd("ping www.baidu.com");
	consoleMain.exeCmd("ipconfig");
	System.out.println("你好，java LC");
	consoleMain.exeCmd("calc");
	
	String path="F:\\JavaCode";
	
	consoleMain.exeCmd("javac"+" "+path+"\\"+"JavacTest.java");
	
	//java
	
	System.out.println("ok");
	}

}
