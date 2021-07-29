package com.reactpro.springsecurityjwt.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthErrorResponse {
	private String message;

}
