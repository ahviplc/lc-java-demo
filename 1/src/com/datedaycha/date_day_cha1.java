package com.datedaycha;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/*
 * java 判断两个时间相差的天数
1、实现目标

　　输入：两个日期

　　输出：两个日期相差的天数

2、代码实现

方法1：

　　通过Calendar类的日期比较。注意：这里需要考虑一下：

　　日期是跨年份的，如一个是2012年，一个是2015年的
　   年份是分闰年和平年的，各自的天数不同
 */
public class date_day_cha1 {
	
	
	/**方法1
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays_one(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        
      //加个时间日期大小 判断  管他谁大谁小
        int diff ;  
        if(day1<day2) {  
            diff = day2 - day1;  
        } else {  
            diff = day1 - day2;  
        }
        
        
        
        if(year1 != year2)  //不同年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            //return timeDistance + (day2-day1) ;
            
            return timeDistance + diff ;
        }
        else     //同一年
        {
//            System.out.println("判断day2 - day1 : " + (day2-day1));
//            return day2-day1;
        	
        	 System.out.println("判断day2 - day1 : " + diff);
             return diff;
        }
    }
    
    
    
   /*　直接通过计算两个日期的毫秒数，他们的差除以一天的毫秒数，即可得到我们想要的两个日期相差的天数。*/

         /**
         * 通过时间秒毫秒数判断两个时间的间隔
         * @param date1
         * @param date2
         * @return
         */
        public static int differentDaysByMillisecond(Date date1,Date date2)
        {
        	//加个时间日期大小 判断  管他谁大谁小
        	  long time1 = date1.getTime();  
              long time2 = date2.getTime();  
              long diff ; 
              
              if(time1<time2) {  
                  diff = time2 - time1;  
              } else {  
                  diff = time1 - time2;  
              } 
        	
            //int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
              int days = (int) (diff /(1000*3600*24));
            return days;
        }
        
        
        
        //测试
        public static void main(String[] args) throws Exception {
			
        	String dateStr = "2017-1-1 0:0:0";
            String dateStr2 = "2017-9-14 10:57:01";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         //   SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try 
            {
                Date date2 = format.parse(dateStr2);
                Date date = format.parse(dateStr);
                
                System.out.println("两个日期的差距 方法1：" + differentDays_one(date,date2));
                System.out.println("两个日期的差距 方法2：" + differentDaysByMillisecond(date,date2));
                
                Date d=new Date();
                System.out.println(d.getTime());
                System.out.println("距离今天-两个日期的差距 方法1：" + differentDays_one(date,d));
                System.out.println("距离今天-两个日期的差距 方法2：" + differentDaysByMillisecond(d,date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
		}

}
