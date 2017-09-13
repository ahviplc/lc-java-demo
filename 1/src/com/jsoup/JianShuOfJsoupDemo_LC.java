package com.jsoup;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/*
 * 简书 - 内容爬虫 -jsoup-已测试-好使！
 * 2017年9月13日15:56:00
 * LC
 * 
 * 
 */


public class JianShuOfJsoupDemo_LC {

	@Test
	  public void parseLCJianShu() {
		  
	        Document document = null;
	        try {
	            //eg1:解析简书
	        	document = Jsoup.connect("http://www.jianshu.com/")
                        .timeout(10000)
                        .get();
	            Elements noteList = document.select("ul.note-list");
	            Elements li = noteList.select("li");
	 
	            for (Element element : li) {
	            	
	            	//标题
	            	String title = element.select("a.title").text();
	            	//文章链接
	            	String titlelink= element.select("a.title").attr("abs:href");
	            	//头像
	            	String avatar = element.select("a.avatar").select("img").attr("src");
	            	
	            	//个人首页链接-首页链接
	            	String authorLink = element.select("a.blue-link").attr("abs:href");
	            	
	            			
	                System.out.println(title );
	                System.out.println(titlelink );
	                System.out.println( avatar);
	                System.out.println(authorLink);
	            }
	             
	             
	       
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 
	    }
	
}
