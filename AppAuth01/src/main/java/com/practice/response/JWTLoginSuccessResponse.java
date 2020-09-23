package com.practice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTLoginSuccessResponse<T> {
	private int code;
	private String message;
	private T payload;
}
