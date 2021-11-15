package com.unionman.mqtt.util;

import java.util.Base64;

import com.unionman.mqtt.common.ErrorCode;
import com.unionman.mqtt.exception.UnionmanException;

public class Base64Util {

	public static final String UTF_8 = "UTF-8";
	public static Base64.Encoder encoder;
	// 即为安全的编码方式，替换“+” “/” “-”为“_”
	public static Base64.Encoder urlEncoder;
	public static Base64.Decoder decoder;
	public static Base64.Decoder urlDecoder;

	static {
		encoder = Base64.getEncoder();
		urlEncoder = Base64.getUrlEncoder();
		decoder = Base64.getDecoder();
		urlDecoder = Base64.getUrlDecoder();
	}

	// encode
	private static byte[] encode(byte[] bytes) {
		return encoder.encode(bytes);
	}

	/**
	 * base64加密
	 * 
	 * @param string
	 * @return
	 */
	public static String encode(String string) {
		byte[] encode = encode(string.getBytes());
		try {
			return new String(encode, UTF_8);
		} catch (Exception e) {
			throw new UnionmanException(ErrorCode.base64_encode_fail.getCode(),
					ErrorCode.base64_encode_fail.getMessage());
		}
	}

	// decode
	private static byte[] decode(byte[] bytes) {
		return decoder.decode(bytes);
	}

	/**
	 * base64解密
	 * 
	 * @param string
	 * @return
	 */
	public static String decode(String string) {
		byte[] decode = decode(string.getBytes());
		try {
			return new String(decode, UTF_8);
		} catch (Exception e) {
			throw new UnionmanException(ErrorCode.base64_decode_fail.getCode(),
					ErrorCode.base64_decode_fail.getMessage());
		}
	}
}
