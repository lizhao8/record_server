package com.common.config.porvider;

import lombok.Data;
/**
 * 自定义参数解析bean
 * @author LIZHAO
 * @date 2020年7月29日
 * @desc 描述
 */
@Data
public class AuthenticationBean {
	private String username;
	private String password;
}