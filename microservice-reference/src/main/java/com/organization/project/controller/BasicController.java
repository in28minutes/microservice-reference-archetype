package com.organization.project.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.bean.WelcomeBean;

@RestController
public class BasicController {

	private static final String helloWorldTemplate = "Hello World, %s!";

	@RequestMapping("/welcome")
	public String welcome() {
		return "Hello World";
	}

	@RequestMapping("/welcome-with-object")
	public WelcomeBean welcomeWithObject() {
		return new WelcomeBean("Hello World");
	}

	@RequestMapping("/welcome-with-parameter/name/{name}")
	public WelcomeBean welcomeWithParameter(@PathVariable String name) {
		return new WelcomeBean(String.format(helloWorldTemplate, name));
	}

}