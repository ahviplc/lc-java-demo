package com.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * 远传解析-报文解析过程测试
 * 16进制字符串与byte数组byte[]互转
 * @author LC
 * @dateTime 2019年3月7日10:46:56
 */
public class yuanchuan_solve_msg_hexStr_bytes_app {

    /*
     * Convert byte[] to hex
     * string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     *
     * @return hex string
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);

        }
        return bytes;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 将7d从报文中去除，返回报文
     *
     * @param resultStr
     * @return
     */
    public static String translate7dString(String resultStr) {
        List<String> resouceList = new ArrayList<>();
        for (int i = 0; i < resultStr.length(); i += 2) {
            String temp = resultStr.substring(i, i + 2);
            resouceList.add(temp);
        }// 把接收到的字符串按字节分割
        resouceList = decodingData(resouceList);// 翻译
        if (resouceList.get(0).equals("68") && resouceList.get(1).equals("68")) {
            resouceList.remove(1);
        }
        StringBuffer mStringBuffer = new StringBuffer();// 翻译后的实际报文
        for (String str : resouceList) {
            mStringBuffer.append(str);
        }
        return mStringBuffer.toString();
    }

    /**
     * 接收报文翻译
     *
     * @param str 可能包含7D的数据
     * @return 翻译结果字符串
     */
    public static List<String> decodingData(List<String> strList) {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {

            if (strList.get(i).equals("7d") || strList.get(i).equals("7D")) {
                System.out.println(Integer.parseInt(strList.get(i + 1), 16));
                int result = Integer.parseInt(strList.get(i + 1), 16) ^ 0x20;
                String str = Integer.toHexString(result);
                str = "00" + str;
                str = str.substring(str.length() - 2);
                returnList.add(str);
                i++;
            } else {
                String str = strList.get(i);
                str = "00" + str;
                str = str.substring(str.length() - 2);
                returnList.add(str);
            }

        }
        return returnList;
    }

    public static void main(String[] args) {
        String string = "}1helloworld1LC";
        byte[] bytes = string.getBytes();
        System.out.println(bytesToHexString(bytes));// [7D 31 68 65 6C 6C 6F 77 6F
        // 72 6C 64 31 4C 43 ]
        // 7d3168656c6c6f776f726c64314c43

        System.out.println(translate7dString("7d317d687d656c6c6f776f726c64314c43"));

        byte[] bb = hexStringToBytes("7d317d687d656c6c6f776f726c64314c43");

        System.out.println(bb);

        byte[] cc = toBytes("7d317d687d656c6c6f776f726c64314c43");

        System.out.println(cc);


    }

}
