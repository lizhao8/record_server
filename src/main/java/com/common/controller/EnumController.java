package com.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.common.base.entity.Result;
import com.common.entity.Enum;
import com.common.entity.Field;

@RestController
@RequestMapping("enum")
public class EnumController extends BaseController<Enum> {

	@GetMapping("getEnum/{entityName}/{fieldName}")
	public Result<Enum> getEnum(@PathVariable String entityName, @PathVariable String fieldName) {
		return null;

	}

	@GetMapping("getEnum/{entityName}")
	public Result<Enum> getEnum(@PathVariable String entityName) {
		return null;

	}
}
