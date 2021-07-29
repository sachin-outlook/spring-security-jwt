package com.reactpro.springsecurityjwt.advice;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationAdvice {

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({AuthenticationException.class, 
		BadCredentialsException.class})
	public JwtAuthErrorResponse authenticationHandler(Exception ex){
		return new JwtAuthErrorResponse(ex.getMessage());
	}
}
