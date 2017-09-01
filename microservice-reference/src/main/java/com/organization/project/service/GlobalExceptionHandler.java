package com.organization.project.service;

import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
//@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) {
		return e.getMessage();
	}

}