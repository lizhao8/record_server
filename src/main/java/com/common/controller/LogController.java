package com.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.common.entity.Log;

@RestController
@RequestMapping("log")
public class LogController extends BaseController<Log>{

}
