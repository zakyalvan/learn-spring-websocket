package com.innovez.learn.chat.controller;

import org.springframework.util.Assert;

public class RegistrationResponse {
	public static final Integer SUCCESS_CODE = 1;
	public static final Integer FAILED_CODE = 2;
		
	private final Integer code;
	private final String message;
		
	public static RegistrationResponse createSuccess(String message) {
		Assert.notNull(message);
		return new RegistrationResponse(SUCCESS_CODE, message);
	}
	public static RegistrationResponse createFailed(String message) {
		Assert.notNull(message);
		return new RegistrationResponse(FAILED_CODE, message);
	}
	
	RegistrationResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
		
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}