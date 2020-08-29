package com.common.config.porvider;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.common.base.entity.Result;
/**
 * 自定义登陆失败处理器
 * @author LIZHAO
 * @date 2020年7月21日
 * @desc 描述
 */
@Component
public class SelflAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {
		Result.BUILD(400, "登陆失败:" + e.getMessage()).writerToResponse(response);
	}
}