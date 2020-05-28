package com.utilsCoAPHH;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMACSHA256 {

    /**
     * sha256_HMAC加密
     *
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = NumberUtils.byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println(("Error HmacSHA256 ===========" + e));
            ;
        }
        return hash;
    }

    /**
     * sha256_HMAC加密
     *
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, byte[] secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret, "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = NumberUtils.byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println(("Error HmacSHA256 ===========" + e));
        }
        return hash;
    }

    public static String sha256_HMAC(byte[] message, byte[] secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret, "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message);
            hash = NumberUtils.byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println(("Error HmacSHA256 ===========" + e));
        }
        return hash;
    }
}
