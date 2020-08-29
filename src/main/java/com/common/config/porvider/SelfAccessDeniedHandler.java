package com.common.config.porvider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.common.base.entity.Result;

@Component
public class SelfAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e)
			throws IOException, ServletException {
		Result.BUILD(401, "权限不足").writerToResponse(response);
	}
}