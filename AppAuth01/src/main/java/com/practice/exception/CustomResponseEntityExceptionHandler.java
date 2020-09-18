package com.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.practice.response.EmailAlreadyExistsResponse;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    public final ResponseEntity<Object> handleUsernameAlreadyExists(EmailAlreadyExistsException ex, WebRequest request){
        EmailAlreadyExistsResponse exceptionResponse = new EmailAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
