package com.common.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.common.entity.User;


@RestController
@RequestMapping("user")
public class UserController extends BaseController<User> {
	
}
