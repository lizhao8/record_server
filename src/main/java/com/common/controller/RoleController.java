package com.common.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.common.entity.Role;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController<Role>{

}
