package com.common.config.porvider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.common.base.entity.Result;

/**
 * 自定义未登录访问处理器
 * 
 * @author LIZHAO
 * @date 2020年7月21日
 * @desc 描述
 */
public class SelfLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	public SelfLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		String error = e.getMessage();
		if (e instanceof InsufficientAuthenticationException) {
			error = "访问此资源需要完全身份验证";
		}
		Result.BUILD(401, "未登录" + error).writerToResponse(response);
	}

}