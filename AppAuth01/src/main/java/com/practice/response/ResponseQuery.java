package com.practice.response;

import lombok.Getter;
import lombok.Setter;
import com.practice.value.HttpStatusCode;

@Getter
@Setter
public class ResponseQuery<T> {
	private Integer code;
	private String message;
	private T payload;

	private ResponseQuery(Integer code, String message, T payload) {
		this.code = code;
		this.message = message;
		this.payload = payload;
	}

	private ResponseQuery(String message, T payload) {
		this.message = message;
		this.payload = payload;
	}

	public static <T> ResponseQuery<T> success(String message, T payload) {
		return new ResponseQuery<T>(HttpStatusCode.VALUE_200.getCode(), message, payload);
	}

	public static <T> ResponseQuery<T> faild(String message, T payload) {
		return new ResponseQuery<T>(HttpStatusCode.VALUE_500.getCode(), message, payload);
	}
}
