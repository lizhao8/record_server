package com.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.common.entity.RoleMenu;

@RestController
@RequestMapping("/role/menu")
public class RoleMenuController extends BaseController<RoleMenu> {

}
