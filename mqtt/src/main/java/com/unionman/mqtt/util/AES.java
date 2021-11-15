package com.unionman.mqtt.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.unionman.mqtt.common.ErrorCode;
import com.unionman.mqtt.exception.UnionmanException;

public class AES {
	
	/**
	 * AES-128-ECB加密
	 * @param msg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String Encrypt(String msg, String key){
		try {
			if (key == null) {
				return null;
			}
			// 判断Key是否为16位
			if (key.length() != 16) {
				return null;
			}
			byte[] raw = key.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(msg.getBytes("utf-8"));
			return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		}catch(Exception e) {
			throw new UnionmanException(ErrorCode.aes_128_ecb_encode_fail.getCode(), ErrorCode.aes_128_ecb_encode_fail.getMessage());
		}
	}

	/**
	 * AES-128-ECB解密
	 * @param msg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String Decrypt(String msg, String key){
		try {
			// 判断Key是否正确
			if (key == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (key.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = key.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = new Base64().decode(msg);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			throw new UnionmanException(ErrorCode.aes_128_ecb_decode_fail.getCode(), ErrorCode.aes_128_ecb_decode_fail.getMessage());
		}
	}

}
