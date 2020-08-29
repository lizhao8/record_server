package com.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.common.entity.UserRole;

@RestController
@RequestMapping("/user/role")
public class UserRoleController extends BaseController<UserRole> {

}
