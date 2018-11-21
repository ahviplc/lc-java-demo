package com.lc.jinzhizhuanhuan;

/**
 * 非法的二进制数异常类
 * 
 * @author LC
 * @dateTime 2018年11月21日10:15:36
 */
public class IllegalBinaryException extends Exception{
	private static final long serialVersionUID = 1L;
	public IllegalBinaryException(){
		
	}
	public IllegalBinaryException(String message){
		super(message);
	}
}