package com.practice.value;

public enum HttpStatusCode {
	HTTP_200(1003, "Action Success"),
	HTTP_401(1000,"Invalid Login"),
	HTTP_403(1001,"Role invalid"),
	HTTP_500(1002,"Token expired");

	private Integer code;
	private String message;
	HttpStatusCode(Integer code, String message){
		this.code = code;
	}
}
