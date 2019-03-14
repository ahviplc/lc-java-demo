package com.lc;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 远传解析-报文解析过程测试
 * 16进制字符串与byte数组byte[]互转
 *
 * @author LC
 * @dateTime 2019年3月7日10:46:56
 * @updateTime 2019年3月13日11:30:21
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
     * Convert hex string to byte[] 十进制表示 7d 31 = 125 49
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
     * 将16进制字符串转换为byte[] 十进制表示 7d 31 = 125 49
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
     * @param strList 可能包含7D的数据
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


    /**
     * java 16进制字符串转二进制（byte[]） 十进制表示 7d 31 = 125 49
     *
     * @param hex
     * @return
     */
    public static byte[] hexStr2Byte(String hex) {
        ByteBuffer bf = ByteBuffer.allocate(hex.length() / 2);
        for (int i = 0; i < hex.length(); i++) {
            String hexStr = hex.charAt(i) + "";
            i++;
            hexStr += hex.charAt(i);
            byte b = (byte) Integer.parseInt(hexStr, 16);
            bf.put(b);
        }
        return bf.array();
    }

    /**
     * 16进制字符串转二进制
     * 92H = 1001 0010B
     * 按位解析
     *
     * @param hexStr
     * @return
     */
    public static String hexStrVersionTo2B(String hexStr) {

        // TODO Auto-generated method stub
        String s = "";
        for (int i = 0; i < hexStr.length(); i++) {
            System.out.println(hexStr.charAt(i));
            s += from16StrTo2Str(hexStr.charAt(i));
        }
        return s;

    }


    /**
     * 范围16进制str转为2进制str
     * 例如：9—>1001  2->0010 H->1111
     *
     * @param charAt
     * @return
     */
    private static String from16StrTo2Str(char charAt) {
        // TODO Auto-generated method stub
        switch (charAt) {
            case '0':
                return "0000";
            case '1':
                return "0001";
            case '2':
                return "0010";
            case '3':
                return "0011";
            case '4':
                return "0100";
            case '5':
                return "0101";
            case '6':
                return "0110";
            case '7':
                return "0111";
            case '8':
                return "1000";
            case '9':
                return "1001";
            case 'A':
                return "1010";
            case 'B':
                return "1011";
            case 'C':
                return "1100";
            case 'D':
                return "1101";
            case 'E':
                return "1110";
            case 'F':
                return "1111";

        }
        return null;
    }

    /**
     * @param buf
     * @return
     * @description 将二进制转换成16进制
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * @param hexStr
     * @return
     * @description 将16进制转换为二进制  十进制表示 7d 31 = 125 49
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 表状态msg解析,注释解析
     * 8位：从头开始1-4：#0无外电，1外接电#         #0阀门无低电，1阀门低电#    #0无卡攻击，1卡攻击#    #0未剪线，1剪线#
     * 5-8：#0远程阀开，1远程阀关#     #0无低电，1低电#           #0阀开，1阀关#          #0已开户，1未开户#
     * 1000 0111
     *
     * @param stateStr
     * @return
     */
    private static String analysisMeterMsgState(String stateStr) {

        if (stateStr == null || stateStr.equals("")) {
            System.out.println("方法名：analysisMeterMsgState(),无解析成的8位2进制,无法进行表状态msg解析!");
            return null;
        }

        String okStr = "";
        for (int i = 0; i < stateStr.length(); i++) {
            //System.out.println(stateStr.charAt(i));
            String charStr = String.valueOf(stateStr.charAt(i));
            if (i == 0) {
                if (charStr.equals("0")) {
                    okStr += "无外电,";
                } else if (charStr.equals("1")) {
                    okStr += "外接电,";
                } else {
                    okStr += "无是否外接电状态,";
                }
            }

            if (i == 1) {
                if (charStr.equals("0")) {
                    okStr += "阀门无低电,";
                } else if (charStr.equals("1")) {
                    okStr += "阀门低电,";
                } else {
                    okStr += "无阀门是否低电状态,";
                }
            }

            if (i == 2) {
                if (charStr.equals("0")) {
                    okStr += "无卡攻击,";
                } else if (charStr.equals("1")) {
                    okStr += "卡攻击,";
                } else {
                    okStr += "无是否有卡攻击状态,";
                }
            }

            if (i == 3) {
                if (charStr.equals("0")) {
                    okStr += "未剪线,";
                } else if (charStr.equals("1")) {
                    okStr += "剪线,";
                } else {
                    okStr += "无是否有剪线状态,";
                }
            }

            if (i == 4) {
                if (charStr.equals("0")) {
                    okStr += "远程阀开,";
                } else if (charStr.equals("1")) {
                    okStr += "远程阀关,";
                } else {
                    okStr += "无远程阀开关状态,";
                }
            }

            if (i == 5) {
                if (charStr.equals("0")) {
                    okStr += "无低电,";
                } else if (charStr.equals("1")) {
                    okStr += "低电,";
                } else {
                    okStr += "无是否低电状态,";
                }
            }

            if (i == 6) {
                if (charStr.equals("0")) {
                    okStr += "阀开,";
                } else if (charStr.equals("1")) {
                    okStr += "阀关,";
                } else {
                    okStr += "无阀开关状态,";
                }
            }

            if (i == 7) {
                if (charStr.equals("0")) {
                    okStr += "已开户";
                } else if (charStr.equals("1")) {
                    okStr += "未开户";
                } else {
                    okStr += "无是否开户状态";
                }
            }

        }
        return okStr;
    }


    public static void main(String[] args) {
//        String string = "}1helloworld1LC";
//        byte[] bytes = string.getBytes();
//        System.out.println(bytesToHexString(bytes));// [7D 31 68 65 6C 6C 6F 77 6F
//        // 72 6C 64 31 4C 43 ]
//        // 7d3168656c6c6f776f726c64314c43
//
//        System.out.println(translate7dString("7d317d687d656c6c6f776f726c64314c43"));
//
//        byte[] bb = hexStringToBytes("7d317d687d656c6c6f776f726c64314c43");  //7d 31 = 125 49
//
//        System.out.println(bb);
//
//        byte[] cc = toBytes("7d317d687d656c6c6f776f726c64314c43"); //7d 31 = 125 49
//
//        System.out.println(cc);
        byte[] bytes2Str = hexStr2Byte("7d31"); //7d 31 = 125 49
        System.out.println(bytes2Str);

        byte[] bytes2Str2 = parseHexStr2Byte("7d31"); //7d 31 = 125 49
        System.out.println(bytes2Str2);

        byte[] bbb = new byte[4];//初始化一个byte数组，长度为2;
        //添加数据
        bbb[0] = 12;
        bbb[1] = 23;
        bbb[2] = 125;
        bbb[3] = 49;

        String sss = parseByte2HexStr(bbb);//12 23 125 49->0C 17 7D 31
        System.out.println(sss);

        String aaa = hexStrVersionTo2B("92");
        System.out.println(aaa);


//        String aaa1 = hexStrVersionTo2B("83");
//        System.out.println(aaa1);
//        String aaa2 = hexStrVersionTo2B("8D");


//        System.out.println(aaa2);
//        System.out.println(aaa2.substring(0, 1));
//        System.out.println(aaa2.substring(1, 2));
//        System.out.println(aaa2.substring(2, 3));
//        System.out.println(aaa2.substring(3, 4));
//        System.out.println(aaa2.substring(4, 5));
//        System.out.println(aaa2.substring(5, 6));
//        System.out.println(aaa2.substring(6, 7));
//        System.out.println(aaa2.substring(7));
//
//        String aaa3 = "12345678";
//        System.out.println(aaa3);
//        System.out.println(aaa3.substring(0, 1));
//        System.out.println(aaa3.substring(1, 2));
//        System.out.println(aaa3.substring(2, 3));
//        System.out.println(aaa3.substring(3, 4));
//        System.out.println(aaa3.substring(4, 5));
//        System.out.println(aaa3.substring(5, 6));
//        System.out.println(aaa3.substring(6, 7));
//        System.out.println(aaa3.substring(7));

        System.out.println(analysisMeterMsgState("10000111"));
        System.out.println(analysisMeterMsgState("11001100"));


//        System.out.println(hexStrVersionTo2B("0123"));
//        System.out.println(hexStrVersionTo2B("4567"));
//        System.out.println(hexStrVersionTo2B("89AB"));
//        System.out.println(hexStrVersionTo2B("CDEF"));


    }


}
