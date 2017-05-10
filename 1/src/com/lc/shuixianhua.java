package com.lc;

import org.junit.Test;

/*
 *  题目：打印出100-999之间所有的"水仙花数"，所谓"水仙花数"是指一个三位数，
 *  其各位数字立方和等于该数本身。
         例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。 
 */
public class shuixianhua {
	
	@Test
	public void ceshi() {
	      int i=657;
	      System.out.println("个位数--0--"+i%100%10);
	      System.out.println("个位数--1--"+i%10);
	      System.out.println("十位数--1--"+i%100/10);
	      System.out.println("十位数--2--"+i/10%10);
	      System.out.println("百位数--1--"+i/100);
	      
	}
	
	
	
    public static void main(String[] args) {
			
    for (int sz = 100; sz <1000; sz++) {
		
      
//       int sw=sz%100/10;
//       int bw=sz/100;
//       int gw=sz%100%10;
       
       int sw=sz%100/10;
       int bw=sz/100;
       int gw=sz%10;

      if(sz==gw*gw*gw+sw*sw*sw+bw*bw*bw){
    	  System.out.println("是水仙花的数有："+sz);
      }
       
     
    	
	}
           
			}
}
