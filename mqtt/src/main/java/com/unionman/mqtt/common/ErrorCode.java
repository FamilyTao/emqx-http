package com.unionman.mqtt.common;

public enum ErrorCode {

	mac_null("000001", "mac地址为空"),
	sha256_hmac_encode_fail("000002", "sha256_hmac_fail加密失败"),
	aes_128_ecb_encode_fail("000003", "aes_128_ecb加密失败"),
	aes_128_ecb_decode_fail("000004", "aes_128_ecb解密失败"),
	username_null("000005", "username为空"),
	password_null("000006", "password为空"),
	map_error("000007", "获取map对应信息错误"),
	base64_encode_fail("000008", "base64加密失败"),
	base64_decode_fail("000009", "base64解密失败"),
	publish_fail("000010", "发布信息失败"),
	macs_null("000011", "发布信息的macs为空");
	
	

	public String code;

	public String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
