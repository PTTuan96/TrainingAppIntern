package com.practice.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DuplicateDataExceptionHandler {

	@ExceptionHandler(value = { DuplicateDataException.class })
	public String handleDataNotFoundException(Exception e, HttpServletRequest request, HttpServletResponse response) {
		return e.getMessage();
	}
}
