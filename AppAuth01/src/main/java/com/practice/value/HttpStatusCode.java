package com.practice.value;

public enum HttpStatusCode {

	VALUE_200(0000), VALUE_500(9999);

	Integer code;

	HttpStatusCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return this.code;
	}
}
