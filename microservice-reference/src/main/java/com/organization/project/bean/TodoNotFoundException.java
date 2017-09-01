package com.organization.project.bean;

public class TodoNotFoundException
		extends RuntimeException {
	public TodoNotFoundException(String msg) {
		super(msg);
	}

}