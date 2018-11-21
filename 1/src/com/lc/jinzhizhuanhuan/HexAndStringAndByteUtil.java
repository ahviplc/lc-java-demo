package com.lc.jinzhizhuanhuan;

import java.io.UnsupportedEncodingException;

/**
 * 
 * 工具1
 * 介绍：16进制值与String/Byte之间的转换
 * @author LC
 * @email ahlc@sina.cn
 * @datatime 2018年11月21日11:08:09
 * http://blog.csdn.net/hzbigdog/article/details/6877712
 * http://www.blogjava.net/lijinglin/archive/2011/11/02/362567.html
 * 
 * @project github
 * @create 2018年11月21日11:08:28
 */
public class HexAndStringAndByteUtil {
	/**
	 * 字符串转换成十六进制字符串
	 * 
	 * @param String
	 *            str 待转换的ASCII字符串
	 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
	 */
	public static String str2HexStr(String str) {

		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;

		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			sb.append(' ');
		}
		return sb.toString().trim();
	}

	/**
	 * 十六进制转换字符串
	 * 
	 * @param String
	 *            str Byte字符串(Byte之间无分隔符 如:[616C6B])
	 * @return String 对应的字符串
	 * @throws UnsupportedEncodingException 
	 */
	public static String hexStr2Str(String hexStr) throws UnsupportedEncodingException {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;

		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		
		//String s=new String(bytes,"UTF-8");//是以UTF-8的编码生成字符串
		
		return new String(bytes);
	}

	/**
	 * bytes转换成十六进制字符串
	 * 
	 * @param byte[] b byte数组
	 * @return String 每个Byte值之间空格分隔
	 */
	public static String byte2HexStr(byte[] b) {
		String stmp = "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
//			sb.append(" ");
		}
		return sb.toString().toUpperCase().trim();
	}

	/**
	 * bytes字符串转换为Byte值
	 * 
	 * @param String
	 *            src Byte字符串，每个Byte之间没有分隔符
	 * @return byte[]
	 */
	public static byte[] hexStr2Bytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		System.out.println(l);
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = Byte.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
		}
		return ret;
	}

	/**
	 * String的字符串转换成unicode的String
	 * 
	 * @param String
	 *            strText 全角字符串
	 * @return String 每个unicode之间无分隔符
	 * @throws Exception
	 */
	public static String strToUnicode(String strText) throws Exception {
		char c;
		StringBuilder str = new StringBuilder();
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128)
				str.append("\\u" + strHex);
			else
				// 低位在前面补00
				str.append("\\u00" + strHex);
		}
		return str.toString();
	}

	/**
	 * unicode的String转换成String的字符串
	 * 
	 * @param String
	 *            hex 16进制值字符串 （一个unicode为2byte）
	 * @return String 全角字符串
	 */
	public static String unicodeToString(String hex) {
		int t = hex.length() / 6;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// 高位需要补上00再转
			String s1 = s.substring(2, 4) + "00";
			// 低位直接转
			String s2 = s.substring(4);
			// 将16进制的string转为int
			int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
			// 将int转换为字符
			char[] chars = Character.toChars(n);
			str.append(new String(chars));
		}
		return str.toString();
	}
	
	/**
	 * str.getBytes("UTF-8"); 的意思是以UTF-8的编码取得字节 
     * new String(XXX,"UTF-8"); 的意思是以UTF-8的编码生成字符串
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		String hex = "ef2c71b29202f3e642f2abd8d518f367ec3fbf6a6a61beb678ae0c871ee368ac";
		//System.out.println(HexAndStringAndByteUtil.hexStr2Str(hex));//不可以直接输出，会乱码
		//String res = new String(HexAndStringAndByteUtil.hexStr2Str(hex).getBytes("utf-8");//byte[] 转 string
		byte[] srtbyte = HexAndStringAndByteUtil.hexStr2Str(hex).getBytes("utf-8");//string 转 byte[]
		//byte[] srtbyte = HexAndStringAndByteUtil.hexStr2Str(hex).getBytes();//string 转 byte[]  也可以
		for(byte bstr:srtbyte){
		System.out.println(bstr);
		}
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println(srtbyte);
		System.out.println(srtbyte.length);
		
		System.out.println(HexAndStringAndByteUtil.strToUnicode("LC"));
		//\u004c\u0043 Unicode编码转换 转中文是 LC
		System.out.println(HexAndStringAndByteUtil.unicodeToString("004c"));//测不出来 不会测
	}
}