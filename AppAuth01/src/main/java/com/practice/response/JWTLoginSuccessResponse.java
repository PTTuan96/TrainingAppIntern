package com.practice.response;

import com.practice.value.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTLoginSuccessResponse<T> {
	HttpStatusCode text;
	private T payload;
}
