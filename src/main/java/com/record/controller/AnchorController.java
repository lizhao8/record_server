package com.record.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.common.base.entity.Result;
import com.record.entity.Anchor;
import com.record.service.AnchorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("anchor")
public class AnchorController extends BaseController<Anchor> {
	
	@Autowired
	AnchorService anchorService;
	@PostMapping("save/{uid}")
	public Result<Anchor> save(@PathVariable Integer uid) {
		return anchorService.saveFromUid(uid);
	}
}
