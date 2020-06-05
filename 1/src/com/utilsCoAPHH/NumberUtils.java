package com.utilsCoAPHH;

import cn.hutool.core.util.HexUtil;
import org.apache.commons.codec.binary.Hex;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

public class NumberUtils {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * 二进制转16进制
     *
     * @param number
     * @return
     */
    public static String binaryToHex(String number) {
        return Integer.toHexString(Integer.valueOf(number, 2));
    }

    /**
     * 16进制转二进制
     *
     * @param number
     * @param length
     * @return
     */
    public static String hexToBinary(String number, Integer length) {
        String binaryString = Integer.toBinaryString(Integer.valueOf(number, 16));
        if (binaryString.length() < length) {
            binaryString = padLeft(binaryString, length);
        }
        return binaryString;
    }

    public static String getDate(Integer num) {
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR)).substring(2, 4);
        String month = String.valueOf(now.get(Calendar.MONTH) + 1);
        if (month.length() < 2) {
            month = "0" + month;
        }
        String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        if (day.length() < 2) {
            day = "0" + day;
        }

        String hour = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
        if (hour.length() < 2) {
            hour = "0" + hour;
        }

        String minute = String.valueOf(now.get(Calendar.MINUTE));
        if (minute.length() < 2) {
            minute = "0" + minute;
        }

        String second = String.valueOf(now.get(Calendar.SECOND));
        if (second.length() < 2) {
            second = "0" + second;
        }
        if (num == 0) {
            return year + month + day + hour + minute + second;
        }
        if (num <= 1) {
            return year;
        } else if (num <= 2) {
            return year + month;
        } else if (num <= 3) {
            return year + month + day;
        } else if (num <= 4) {
            return year + month + day + hour;
        } else if (num <= 5) {
            return year + month + day + hour + minute;
        } else {
            return year + month + day + hour + minute + second;
        }
    }

    /**
     * 字符串左补0
     *
     * @param str       要补零的值
     * @param strLength 补完零之后的总长度
     * @return String
     */
    public static String padLeft(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append("0").append(str);// 左补0
            // sb.append(str).append("0");//右补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    /**
     * 字符串右补0
     *
     * @param str       要补零的值
     * @param strLength 补完零之后的总长度
     * @return String
     */
    public static String padRight(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append(str).append("0");//右补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    /**
     * 将字符串转成字节数组
     *
     * @param hexItr
     * @return
     */
    public static byte[] hexItr2Arr(String hexItr) {
        return HexUtil.decodeHex(hexItr);
    }

    /**
     * 将字符串转成字节数组2 和 上面方法 hexItr2Arr 一样的
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static byte[] HexStringTobyteArray(String hexItr) throws Exception {
        return Hex.decodeHex(hexItr.toCharArray());
    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        return Hex.encodeHexString(b);
    }

    private static boolean isF(String hex) {
        int f = 0;
        for (int i = 0; i < hex.length(); i++) {
            if ("f".equals(hex.substring(i, i + 1))) {
                f++;
            }
        }
        return f == hex.length();
    }


    public static BigDecimal hexToDecimal(String hex, int decimal) {
        if (hex.length() >= 8 && isF(hex)) {
            return new BigDecimal(0);
        }

        long val = new BigInteger(hex, 16).longValue();
        int devide = 1;
        for (int i = 0; i < decimal; i++) {
            devide = devide * 10;
        }
        return new BigDecimal(val).setScale(decimal, BigDecimal.ROUND_DOWN).divide(new BigDecimal(devide).setScale(decimal, BigDecimal.ROUND_DOWN), BigDecimal.ROUND_DOWN);
    }

    public static String decimalToHex(BigDecimal bigDecimal, int decimal) {
        int multi = 1;
        for (int i = 0; i < decimal; i++) {
            multi = multi * 10;
        }
        bigDecimal = bigDecimal.multiply(new BigDecimal(multi));
        return Integer.toHexString(bigDecimal.intValue());
    }

    public static Integer hexToInt(String hex) {
        return Integer.parseInt(hex, 16);
    }


    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    /**
     * 字符串数转hex字符串 带转之前乘以multiplicationFactor 和转之后 前面补零 共截取cutLeng长度
     *
     * @param oldString            老值
     * @param multiplicationFactor 乘法因子
     * @param cutLength            截取左补零总长度
     * @return 处理好的值
     * <p>
     * 输出示例:
     * System.out.println(NumberUtils.parseStringToHexString("520","1",8)); // 00000208 十六进制对应十进制为:520
     * System.out.println(NumberUtils.parseStringToHexString("520","100",8)); // 0000cb20 十六进制对应十进制为:52000
     * System.out.println(NumberUtils.parseStringToHexString("520","100",8)); // 0000cb20 十六进制对应十进制为:52000
     * System.out.println(NumberUtils.parseStringToHexString("100.11","100",8)); // 0000271b 十六进制对应十进制为:10011
     * System.out.println(NumberUtils.parseStringToHexString("200.66","1000",8)); // 00030fd4 十六进制对应十进制为:200660
     * System.out.println(NumberUtils.parseStringToHexString("100","1",4)); // 0064 十六进制对应十进制为:100
     * System.out.println(NumberUtils.parseStringToHexString("255","1",4)); // 00ff 十六进制对应十进制为:255
     */

    public static String parseStringToHexString(String oldString, String multiplicationFactor, int cutLength) {
        // 首先将oldString和乘法因子转成bd
        BigDecimal oldStringBD = new BigDecimal(oldString);
        BigDecimal multiplicationFactorBD = new BigDecimal(multiplicationFactor);
        // 将oldStringBD和multiplicationFactorBD相乘
        BigDecimal multiplyBD = oldStringBD.multiply(multiplicationFactorBD);
        // 将multiplyBD转成16进制
        String multiplyBDHexString = Long.toString(multiplyBD.longValue(), 16);
        // 将 multiplyBDHex根据cutLength左补零
        String realString = padLeft(multiplyBDHexString, cutLength);
        return realString;
    }
}
