package com.utilsCoAPHH;


/**
 * java中十六进制和ASCII码互相转换
 */
public class HexAsciiToStringUtils {

    /**
     * @param str
     * @return
     */
    public static String StringToHexAscii(String str) {
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }

    public static String HexAsciiToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {
            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("\n-----ASCII码转换为16进制 -----");
        String str = "POS88884";
        System.out.println("字符串: " + str);
        String hex = StringToHexAscii(str); // 504F533838383834
        System.out.println("转换为16进制ASCII : " + hex);
        System.out.println(StringToHexAscii("866971036411523")); // 383636393731303336343131353233

        System.out.println("\n***** 16进制转换为ASCII *****");
        System.out.println("Hex ASCII : " + hex);
        System.out.println("String : " + HexAsciiToString(hex)); // POS88884
        System.out.println(HexAsciiToString("383636393731303336343131353233")); // 866971036411523
        System.out.println(HexAsciiToString("3131323532303032303431393030303530303030303030303030303030303030")); // 11252002041900050000000000000000
    }
}
