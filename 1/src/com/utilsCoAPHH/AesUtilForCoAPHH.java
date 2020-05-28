package com.utilsCoAPHH;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public class AesUtilForCoAPHH {
    private static final String EncryptAlg = "AES";

    private static final String Cipher_Mode = "AES/ECB/NOPadding";
    private static final String Cipher_Mode_PKCS7 = "AES/ECB/PKCS7Padding";
    private static final String Encode = "UTF-8";

    private static final int Secret_Key_Size = 16;

    private static final String Key_Encode = "UTF-8";
    private static final int INIT_SIZE = 128;

    /**
     * AES/ECB/PKCS7Padding 加密
     *
     * @param content
     * @param key     密钥
     * @return aes加密后 转base64
     * @throws Exception
     */
    public static byte[] aesPKCS7PaddingEncryptBytes(byte[] content, byte[] key) {

        byte[] resContent = null;

        try {

            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            /*自己构造 0 补位*/

            int paddingLength = 16 - content.length % 16;
            if (paddingLength != 0) {
                resContent = new byte[content.length + paddingLength];
                for (int i = 0; i < content.length; i++) {
                    resContent[i] = content[i];
                }
                for (int i = 0; i < paddingLength; i++) {
                    resContent[content.length + i] = 0;
                }
            } else {
                resContent = content;
            }

            Cipher cipher = null;
            if (resContent.length % 16 == 0) {
                cipher = Cipher.getInstance(Cipher_Mode);
            } else {
                cipher = Cipher.getInstance(Cipher_Mode_PKCS7);
            }

            byte[] realKey = getSecretKey(key);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(realKey, EncryptAlg));
            return cipher.doFinal(resContent);
        } catch (Exception e) {

            //log.error("AES加密失败：content=" +new String(resContent) +" key="+new String(key));
            System.out.println("AES加密失败：content=" + new String(resContent) + " key=" + new String(key));
            return new byte[]{};
        }
    }


    /**
     * AES/ECB/PKCS7Padding 解密
     *
     * @param content
     * @param key     密钥
     * @return 先转base64 再解密
     * @throws Exception
     */
    public static byte[] aesPKCS7PaddingDecrypt(byte[] content, byte[] key) {
        try {
//            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(Cipher_Mode);
            byte[] realKey = getSecretKey(key);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(realKey, EncryptAlg));
            return cipher.doFinal(content);
        } catch (Exception e) {
            //   log.error("AES解密失败：Aescontent = " +e.fillInStackTrace(),e);
            System.out.println("AES解密失败：Aescontent = " + e.fillInStackTrace() + e);
            return new byte[]{};
        }
    }

    /**
     * 对密钥key进行处理：如密钥长度不够位数的则 以指定paddingChar 进行填充；
     * 此处用空格字符填充，也可以 0 填充，具体可根据实际项目需求做变更
     *
     * @param byteKey
     * @return
     * @throws Exception
     */
    public static byte[] getSecretKey(byte[] byteKey) throws Exception {
        final byte paddingChar = ' ';
        byte[] realKey = new byte[Secret_Key_Size];
        for (int i = 0; i < realKey.length; i++) {
            if (i < byteKey.length) {
                realKey[i] = byteKey[i];
            } else {
                realKey[i] = paddingChar;
            }
        }

        return realKey;

    }
}
