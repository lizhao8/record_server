package com.common.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bilibili.entity.outer.User;
import com.bilibili.entity.outer.RoomInfo;
import com.bilibili.entity.outer.RoomBaseInfo;
import com.bilibili.util.Api;

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
	RestTemplate restTemplate;


	@GetMapping("test")
	public Object login(HttpServletRequest request, HttpServletResponse response){
		/*int uid = 2154078;
		User user= Api.getUserInfo(uid);
		Room room = Api.getRoomInfo(uid);
		Live live=Api.getLiveInfo(room.getRoomid());*/
		Object object=Api.getRoomPlayInfo(614957);
		return null;
		
	}
}
