package com.record.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.controller.BaseController;
import com.record.entity.Url;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("url")
public class UrlController extends BaseController<Url> {

}
