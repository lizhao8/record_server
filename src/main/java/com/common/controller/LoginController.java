package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.entity.Result;
import com.common.entity.User;
import com.common.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "getLoginUser")
	public Result<User> getLoginUser() {
		Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
		return Result.SUCCESS((User) userService.loadUserByUsername(authenticationToken.getPrincipal().toString()));
	}
}
