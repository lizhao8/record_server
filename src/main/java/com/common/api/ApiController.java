package com.common.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.base.entity.Result;
import com.record.entity.Anchor;
import com.record.service.AnchorService;

/**
 * 
 * @author zhao_li
 * @ClassName: ApiController
 * @Package com.common.api
 * @Description: TODO
 * @date 2018-9-10 上午10:47:28
 */
@RestController
@RequestMapping("api")
public class ApiController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * @ApiOperation(value="登录接口测试",httpMethod="POST",notes="用户登录")
	 * 
	 * @ApiImplicitParams({
	 * 
	 * @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType
	 * = "String"),
	 * 
	 * @ApiImplicitParam(name = "password", value = "密码", required = true, dataType
	 * = "String") })
	 * 
	 * @RequestMapping(value="login",method=RequestMethod.POST)
	 * 
	 * @ResponseBody public String login(String username,String
	 * password,HttpServletRequest request,HttpServletResponse response) {
	 * logger.info("登录接口测试"); return "1"; }
	 */

	@Autowired
	AnchorService anchorService;

	@GetMapping("saveAnchor/{uid}")
	public Result<Anchor> saveAnchor(@PathVariable Integer uid) {
		return anchorService.saveFromUid(uid);
	}
}
