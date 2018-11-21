package com.lc.jinzhizhuanhuan;

/**
 * 非法的数字异常类
 * 
 * @author LC
 * @dateTime 2018年11月21日10:15:43
 */
public class IllegalNumberException extends Exception{
	private static final long serialVersionUID = 1L;
	public IllegalNumberException(){
		
	}
	public IllegalNumberException(String message){
		super(message);
	}
}