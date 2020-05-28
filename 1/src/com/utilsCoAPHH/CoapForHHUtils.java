package com.utilsCoAPHH;

public class CoapForHHUtils {

    public final static String primary_key = NumberUtils.byteArrayToHexString(new byte[]{0X01, 0X02, 0X03, 0X04, 0X05, 0X06, 0X07, 0X08, 0X09, 0X0A, 0X0b, 0X0c, 0x0d, 0x0e, 0x0f, 0x10});

    public static void main(String[] args) {
        String did = "1";
        String body = "68000100ab0141300120052716515400040001103131323532303032303431393030303530303030303030303030303030303030000100000100100000eb55d721e30d4f59fbe5c791f39d5fe9ffae000d00924c9870000000053836383136333034343230303036384d3533313100000000004d353331312d4d434d4830533033000000000000011b44e372866714d0e831188d7dba68a59e76de47b0530fd70a5736a7946cef4c5f3516";
        String openBody = "680001003C0288000116097FB062DF511D0A4B56E227609874AD040F602EC70ECA9F5E5EDE4B620A3809A8203D1AFB07161454ACC12EBD789C6A8416";
        String data = body.substring(18, body.length() - 70);
        // 认证
        System.out.println(verifyMac(did, body));
        // 加密
        System.out.println(getAesData(did, "00"));
        // 解密
        System.out.println(decryptData(did, "16097fb062df511d0a4b56e227609874"));
        // 消息序号开始到数据域(加密或认证)
        System.out.println(CRC_XModem("01413001" + "20052716515400040001103131323532303032303431393030303530303030303030303030303030303030000100000100100000eb55d721e30d4f59fbe5c791f39d5fe9ffae000d00924c9870000000053836383136333034343230303036384d3533313100000000004d353331312d4d434d483053303300000000000001" + "1b44e372866714d0e831188d7dba68a59e76de47b0530fd70a5736a7946cef4c"));
    }

    //randCode 通讯随机码
    //primaryKey 定死的
    //getAesData 加密
    //getMac 认证
    public static boolean verifyMac(String deviceId, String body) {
        if (body.length() >= 272) {
            String data = body.substring(18, body.length() - 70);
            //20052716515400040001103131323532303032303431393030303530303030303030303030303030303030000100000100100000eb55d721e30d4f59fbe5c791f39d5fe9ffae000d00924c9870000000053836383136333034343230303036384d3533313100000000004d353331312d4d434d483053303300000000000001
            String mac = getMac(deviceId, data);
            System.out.println(mac);
            return body.contains(mac);
        }
        return false;
    }

    public static String getMac(String deviceId, String data) {
        String code = "eb55d721e30d4f59fbe5c791f39d5fe9";
        String key = getHashKey(code, primary_key);
        return HMACSHA256.sha256_HMAC(NumberUtils.hexItr2Arr(code + data), NumberUtils.hexItr2Arr(key));
    }

    public static String getAesData(String deviceId, String data) {
        String code = "eb55d721e30d4f59fbe5c791f39d5fe9";
        String key = getAesKey(code, primary_key);
        return NumberUtils.byteArrayToHexString(AesUtilForCoAPHH.aesPKCS7PaddingEncryptBytes(NumberUtils.hexItr2Arr(data), NumberUtils.hexItr2Arr(key)));
    }

    public static String decryptData(String deviceId, String data) {
        String code = "eb55d721e30d4f59fbe5c791f39d5fe9";
        String aesKey = getAesKey(code, primary_key);
        byte[] bytes = AesUtilForCoAPHH.aesPKCS7PaddingDecrypt(NumberUtils.hexItr2Arr(data), NumberUtils.hexItr2Arr(aesKey));
        return NumberUtils.byteArrayToHexString(bytes);
    }

    public static String getAesKey(String code, String primaryKey) {
        if (HMACSHA256.sha256_HMAC(NumberUtils.hexItr2Arr(code), NumberUtils.hexItr2Arr(primaryKey)).length() > 32) {
            return HMACSHA256.sha256_HMAC(NumberUtils.hexItr2Arr(code), NumberUtils.hexItr2Arr(primaryKey)).substring(0, 32);
        } else {
            return HMACSHA256.sha256_HMAC(NumberUtils.hexItr2Arr(code), NumberUtils.hexItr2Arr(primaryKey));
        }
    }

    public static String getHashKey(String code, String primaryKey) {
        byte[] aesKeyByte = new byte[0];
        try {
            aesKeyByte = AesUtilForCoAPHH.aesPKCS7PaddingEncryptBytes(NumberUtils.hexItr2Arr(code), NumberUtils.hexItr2Arr(primaryKey));
        } catch (Exception e) {
            return "";
        }

        if (NumberUtils.byteArrayToHexString(aesKeyByte).length() > 32)
            return NumberUtils.byteArrayToHexString(aesKeyByte).substring(0, 32);
        else
            return NumberUtils.byteArrayToHexString(aesKeyByte);
    }

    /*crc校验*/
    private static String CRC_XModem(String data) {
        byte[] bytes = NumberUtils.hexStringToByteArray(data);
        int crc = 0x00;          // initial value
        int polynomial = 0x1021;
        for (int index = 0; index < bytes.length; index++) {
            byte b = bytes[index];
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }
        crc &= 0xffff;
        String res = Integer.toHexString(crc);
        if (res.length() < 4) {
            res = NumberUtils.padLeft(res, 4);
        }
        return res;
    }
}
