package com.hutoolDemo;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import junit.framework.Assert;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * RSATest.java - looly/hutool - GitHub1s
 * https://github1s.com/looly/hutool/blob/HEAD/hutool-crypto/src/test/java/cn/hutool/crypto/test/asymmetric/RSATest.java#L42
 */
public class RSADemo {
	public static void main(String[] args) throws UnsupportedEncodingException {

		String privateKey_bs64 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIcOGr7XKVqeYXuBAYmhB7sQXa0PwImthBuSytfrFvF4GhsSWCcH/YRxIwsgX2hh8GM0MI8GJxILDty0JQMHnBJb1oMMSeack9Xq8/nCt93YLkunC9Tv34Z6/j7LraGUoDu9xU7C7SVYhGi8JdbzpyjnietCb+ITG9IhfyFtV84bAgMBAAECgYB2UM7xfYGHF9WC4yO7tM0W/mFyqagddVuU5Ga7/gJdjMAiE1IS75cTniH7L2MmCRcVcbVQty0nS3S7uT3rh5Eb5jmAB1gxEX0Na5HjLgxGp7i2LCWyEDY+45d498raG6vhOSg0edTrepQnyJP5Zjo6Vif777yRwVHSKGx6irn3MQJBAMFgENcZyJi7UnYiJL52FNgD+x+Po4PKuJvQIqA6KHcG9Yg5ei+4YOFFABX0PKnb8iHyQaGmYohux5KeuHIHCWcCQQCyyvU4/qz+YzlbQ8CPS4dwFccRdcxSo0Jh0E1qnad58QCg9lhHuHnPJqSLx9kTp/yQfHaX6WhvvM0WYMRWiEEtAkBwgSSrELwtZ+adBlZcXTTZTM+g2B/pAFuRg3FxyQRb47ecLtyQwPPUfCGFpFha9rUT3O8kTjpLaDBCdIeZB9EHAkBCqV+C75mqg25YM8uw4bVOuh/tx6D10r8mXxbuQHe4BH56hT7nBsYL0VmacFnkdUa6zF0fSLxSpO9Gf6/Un5rFAkEAvF6ENrVU3+HkvAcF/Pknh2Mbh5EH6GoJs4K4CQsxCG7UR+sAWREI83PFQexD2q5ALG+/QlCI0vbOFTYDkBUCrQ==";

		String publicKey_bs64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHDhq+1ylanmF7gQGJoQe7EF2tD8CJrYQbksrX6xbxeBobElgnB/2EcSMLIF9oYfBjNDCPBicSCw7ctCUDB5wSW9aDDEnmnJPV6vP5wrfd2C5LpwvU79+Gev4+y62hlKA7vcVOwu0lWIRovCXW86co54nrQm/iExvSIX8hbVfOGwIDAQAB";

		//		KeyPair pair = KeyUtil.generateKeyPair("RSA");
		//		byte[] privateKey = pair.getPrivate().getEncoded();
		//		byte[] publicKey = pair.getPublic().getEncoded();
		//		RSA rsa = SecureUtil.rsa(privateKey, publicKey);

		// RSA rsa = new RSA();

		// 好方法1
		// PrivateKey privateKey = KeyUtil.generateRSAPrivateKey(Base64.decode(privateKey_bs64));
		// PublicKey publicKey = KeyUtil.generateRSAPublicKey(Base64.decode(publicKey_bs64));
		// RSA rsa = SecureUtil.rsa(privateKey.getEncoded(), publicKey.getEncoded());

		// 好方法2
		RSA rsa = SecureUtil.rsa(privateKey_bs64, publicKey_bs64);

		//获得私钥
		//		rsa.getPrivateKey();
		System.out.println(rsa.getPrivateKeyBase64());
		//获得公钥
		//		rsa.getPublicKey();
		System.out.println(rsa.getPublicKeyBase64());

		//公钥加密，私钥解密
		byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
		byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

		//Junit单元测试
		Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

		//私钥加密，公钥解密
		byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
		byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);

		//Junit单元测试
		Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));


		// 公钥加密
		// 私钥解密

		//		String my = "123";
		//		String bs64Temp = Base64.encode(my);
		//		System.out.println(bs64Temp); // MTIz
		//		System.out.println(Base64.decode(bs64Temp)); // 123
		// System.out.println(StrUtil.str(Base64.decode(bs64Temp), CharsetUtil.CHARSET_UTF_8));
		// ====================================
		// 【1】
		// 加密 先 base64编码 再 encrypt加密
		// 解密 先 decrypt解密 再 base64解码

		// 加
		String encode = Base64.encode("123");
		System.out.println(encode); // MTIz
		byte[] encryptTest = rsa.encrypt(StrUtil.bytes(encode, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);

		System.out.println(Base64.encode(encryptTest)); // Cs2wlOkIEl92kbSCZBIlxjbNnunchQIljCAp4r49p2HJUXK9BW7+E5U5tBlseXPIiPfXkTv1ns9KXYHO1ynYWmHIZSbeWeBRZEmgdWSI4gDh53sGL88+EkoSxaKcdq5CUJ28J7Y7ujiz9btElmfnf52OtuAn5CYItRVDPKf7FGA=

		// 解
		byte[] decryptTest = rsa.decrypt(encryptTest, KeyType.PrivateKey);
		System.out.println(StrUtil.str(decryptTest, CharsetUtil.CHARSET_UTF_8)); // MTIz
		byte[] x = Base64.decode(decryptTest);
		// System.out.println(x);
		System.out.println(StrUtil.str(x, CharsetUtil.CHARSET_UTF_8)); // 123
		System.out.println(new String(x, "UTF-8")); // 123


		// ====================================
		// 【2】
		// 加密 先 encrypt加密 再 base64编码
		// 解密 先 base64解码  再 decrypt解密
		String temp = "456";

		// 加
		byte[] encryptTest2 = rsa.encrypt(StrUtil.bytes(temp, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
		String encode2 = Base64.encode(encryptTest2);
		System.out.println(encode2); // XlZKLZGTMM84G5CZWAXsiYQ1aedQ01d+OqwXp69Y0yMfcp7M2i+e48ms5+KpfSSUPxoB0+3AHOecLu6TMM0uC7+YIppdsFKop5Sc5vMpn8TH8QW4SHT7SgaiEr/rhBjoDx2oYf09frgR24hGIrvs/+S/ctoQSFAzgk/lx/j8KDc=

		// 解

		byte[] s = Base64.decode(encode2);
		// System.out.println(new String(s, "UTF-8")); // 乱码 需要再decrypt即可看见明文

		byte[] decryptTest2 = rsa.decrypt(s, KeyType.PrivateKey);
		System.out.println(StrUtil.str(decryptTest2, CharsetUtil.CHARSET_UTF_8)); // 456
		System.out.println(StrUtil.utf8Str(decryptTest2)); // 456
		System.out.println(new String(decryptTest2, "UTF-8")); // 456

		// 已在这测试通过
		// RSA在线加密/在线解密 公钥加密,公钥解密,私钥加密,私钥解密 - The X 在线工具
		// https://the-x.cn/cryptography/Rsa.aspx

		// Base64解码 Base64编码 UTF8 GB2312 UTF16 GBK 二进制 十六进制 解密 - The X 在线工具
		// https://the-x.cn/base64


		// DigestTest.java - looly/hutool - GitHub1s
		// https://github1s.com/looly/hutool/blob/HEAD/hutool-crypto/src/test/java/cn/hutool/crypto/test/digest/DigestTest.java#L61-L67
		// sha1
		String testStr = "test中文";
		String sha1Hex1 = DigestUtil.sha1Hex(testStr);

		byte[] sha1HexByte = DigestUtil.sha1(testStr);
		// System.out.println(HexUtil.encodeHex(sha1HexByte, true)); // ecabf586cef0d3b11c56549433ad50b81110a836
		String sha1Hex3 = HexUtil.encodeHexStr(sha1HexByte, true);
		System.out.println(sha1Hex3); // ecabf586cef0d3b11c56549433ad50b81110a836


		Assert.assertEquals("ecabf586cef0d3b11c56549433ad50b81110a836", sha1Hex1);
		String sha1Hex2 = DigestUtil.sha1Hex(IoUtil.toStream(testStr, CharsetUtil.CHARSET_UTF_8));
		Assert.assertEquals("ecabf586cef0d3b11c56549433ad50b81110a836", sha1Hex2);
		Assert.assertEquals(sha1Hex3, sha1Hex1);
	}
}
