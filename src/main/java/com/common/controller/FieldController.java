package com.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.common.base.entity.Result;
import com.common.entity.Field;
import com.common.service.FieldService;

@RestController
@RequestMapping("field")
public class FieldController extends BaseController<Field> {
	@Autowired
	FieldService fieldService;

	@GetMapping("getField/{entityName}")
	public Result<List<Field>> getField(@PathVariable String entityName) {
		return Result.SUCCESS(fieldService.getField(entityName));
	}
}
