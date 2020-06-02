package com.utilsCoAPHH;

public class CoapForHHUtils {

    public final static String primary_key = NumberUtils.byteArrayToHexString(new byte[]{0X01, 0X02, 0X03, 0X04, 0X05, 0X06, 0X07, 0X08, 0X09, 0X0A, 0X0b, 0X0c, 0x0d, 0x0e, 0x0f, 0x10});

    public static void main(String[] args) throws Exception {
        String code = "eb55d721e30d4f59fbe5c791f39d5fe9";
        String did = "1";
        String body = "68000100ab0141300120052716515400040001103131323532303032303431393030303530303030303030303030303030303030000100000100100000eb55d721e30d4f59fbe5c791f39d5fe9ffae000d00924c9870000000053836383136333034343230303036384d3533313100000000004d353331312d4d434d4830533033000000000000011b44e372866714d0e831188d7dba68a59e76de47b0530fd70a5736a7946cef4c5f3516";
        String openBody = "680001003C0288000116097FB062DF511D0A4B56E227609874AD040F602EC70ECA9F5E5EDE4B620A3809A8203D1AFB07161454ACC12EBD789C6A8416";
        String data = body.substring(18, body.length() - 70);
        // 认证
        System.out.println(verifyMac(did, code, body));
        // 加密
        System.out.println(getAesData(did, code, "00")); // 16097fb062df511d0a4b56e227609874
        // 解密
        System.out.println(decryptData(did, code, "16097fb062df511d0a4b56e227609874"));
        // 消息序号开始到数据域(加密或认证)
        System.out.println(CRC_XModem("01413001" + "20052716515400040001103131323532303032303431393030303530303030303030303030303030303030000100000100100000eb55d721e30d4f59fbe5c791f39d5fe9ffae000d00924c9870000000053836383136333034343230303036384d3533313100000000004d353331312d4d434d483053303300000000000001" + "1b44e372866714d0e831188d7dba68a59e76de47b0530fd70a5736a7946cef4c"));
        //HexAscii转String
        System.out.println(HexAsciiToStringUtils.HexAsciiToString("383636393731303336343131353233")); // 866971036411523
        System.out.println(NumberUtils.getDate(0)); // 200528215039
        System.out.println(body.substring(14, 18)); // 3001 为功能码
        System.out.println(body.substring(122, 154)); // eb55d721e30d4f59fbe5c791f39d5fe9 为randCode 通讯随机码
        System.out.println(body.substring(180, 210)); // 383638313633303434323030303638
        System.out.println(HexAsciiToStringUtils.HexAsciiToString(body.substring(180, 210))); // 868163044200068
        byte[] bytesTemp = NumberUtils.HexStringTobyteArray("0102030405060708090a0b0c0d0e0f10");
        System.out.println(bytesTemp); // [B@6ed3ccb2
        System.out.println(NumberUtils.byteArrayToHexString(bytesTemp)); // 0102030405060708090a0b0c0d0e0f10
        byte[] bytesTemp2 = new byte[]{0X01, 0X02, 0X03, 0X04, 0X05, 0X06, 0X07, 0X08, 0X09, 0X0A, 0X0b, 0X0c, 0x0d, 0x0e, 0x0f, 0x10};
        System.out.println(NumberUtils.byteArrayToHexString(bytesTemp2)); // 0102030405060708090a0b0c0d0e0f10
        // 加密3003 20060114044400000000000100000000000016ce6100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000181c00200000138800000000000000000000000000000000000000000b0b0b0b0b0b0b0b0b0b0b
        System.out.println(decryptData(did, "8f05ee23f6d1a0acbd0226178e6d8aa2", "c19ba2d55d4495ccc0c8863e3ea3d78bfd926f3a0c2fecefb6d9f2ac2288ec912d3803a5ee7df8eb2e008bdc905675942d3803a5ee7df8eb2e008bdc905675942d3803a5ee7df8eb2e008bdc905675942d3803a5ee7df8eb2e008bdc905675942d3803a5ee7df8eb2e008bdc9056759456a6f7a29105a0abf5cef5a5d8a0982fe929a0e08a578c4b4161e3a9e4c9ac5dfe28a1cbf09d14b25df25cae61be1dd4"));
        // 加密3003 20060114510400000000000100000000000016cb6100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000181c00200000138800000000000000000000000000000000000000000b0b0b0b0b0b0b0b0b0b0b
        System.out.println(decryptData(did, "690666a6cdd69bcf963ad96ac1f915f5", "5737efce287782e9216c1182abdc7e7dcbbc4e555ec367dc7d71eb4146964201cf72e0b4913eb9dd45957fca1dbe3f6fcf72e0b4913eb9dd45957fca1dbe3f6fcf72e0b4913eb9dd45957fca1dbe3f6fcf72e0b4913eb9dd45957fca1dbe3f6fcf72e0b4913eb9dd45957fca1dbe3f6feccf762d32c4424ae4acfa06b865ab817766e053bcb4c8c6bf768cf997f520249733b0ee0dfdd63bea8fe576e1db3258"));
    }

    //randCode 通讯随机码
    //primaryKey 定死的
    //getAesData 加密
    //getMac 认证
    public static boolean verifyMac(String deviceId, String code, String body) {
        if (body.length() >= 272) {
            String data = body.substring(18, body.length() - 70);
            //20052716515400040001103131323532303032303431393030303530303030303030303030303030303030000100000100100000eb55d721e30d4f59fbe5c791f39d5fe9ffae000d00924c9870000000053836383136333034343230303036384d3533313100000000004d353331312d4d434d483053303300000000000001
            String mac = getMac(deviceId, code, data);
            System.out.println(mac);
            return body.contains(mac);
        }
        return false;
    }

    public static String getMac(String deviceId, String code, String data) {
        String key = getHashKey(code, primary_key);
        return HMACSHA256.sha256_HMAC(NumberUtils.hexItr2Arr(code + data), NumberUtils.hexItr2Arr(key));
    }

    public static String getAesData(String deviceId, String code, String data) {
        String key = getAesKey(code, primary_key);
        return NumberUtils.byteArrayToHexString(AesUtilForCoAPHH.aesPKCS7PaddingEncryptBytes(NumberUtils.hexItr2Arr(data), NumberUtils.hexItr2Arr(key)));
    }

    public static String decryptData(String deviceId, String code, String data) {
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
