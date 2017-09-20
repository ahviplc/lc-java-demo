package com.lc;
import java.util.Scanner;
public class money2bigmoney {
	

	/**
	 * <p>输入一串数字，显示汉字读法 出来,如1234,读作：一千二百三十四</p>
	 * 
	 * @author Shockang
	 *
	 */
	

	    private static Scanner scanner;// 方便获取控制台输入

	    private static String input;// 输入的数字字符串
	        
	    // 单个数字中文表示
	    private static String numbers[] = 
	    { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
	      

	    private static String units[] = 
	    { "", "十", "百", "千", "万", "十", "百", "千", "亿" };// 单位
	        
	    // 将数字字符串串中的每个数字字符按照从左到右的顺序放入数组
	    private static String result[];


	    /**
	     * <p> 输入一串数字，显示汉字读法的方法</p>
	     * 
	     * @param input
	     * @return String
	     */
	    public static String getOutput(String input) 
	    {
	        if(input.length()>9)
	        {
	            return "最多输入9位数字";
	        }
	        StringBuffer sb = new StringBuffer();
	        int back = 0;
	        result = new String[input.length()];// result数组初始化
	        for (int i = 0; i < result.length; i++) 
	        {
	            result[i] = String.valueOf(input.charAt(i));
	        }
	        
	        for (int i = 0; i < result.length; i++) 
	        {
	            if (!result[i].equals("0")) 
	            {
	                back = result.length - i - 1;
	                
	                sb.append(numbers[Integer.parseInt(result[i])]);

	                sb.append(units[back]);
	            } 
	            else 
	            {
	                if(i == result.length - 5 && result[i].equals("0"))
	                {
	                    if(result.length==9 && 
	                       result[1].equals("0") && 
	                       result[2].equals("0") && 
	                       result[3].equals("0"))
	                    {
	                        sb.append(numbers[0]);
	                    }
	                    else
	                    {
	                        sb.append(units[4]);
	                    }
	                }
	                else
	                {
	                    if ( i<result.length-1 && !result[i + 1].equals("0")) 
	                    {
	                        sb.append(numbers[0]);
	                    }
	                }
	            }
	        }
	        return sb.toString();
	    }

	    public static void main(String[] args) 
	    {
	        System.out.println("请输入一串数字：如1234，最多9位");
	        scanner = new Scanner(System.in);
	        input = scanner.nextLine();
	        System.out.println(getOutput(input));
	    }

}
