package com.common.config.porvider;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 自定义参数解析
 * springsecurity不支持json传参,登录使用json传参,进行参数转换
 * @author LIZHAO
 * @date 2020年7月29日
 * @desc 描述
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// attempt Authentication when Content-Type is json
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
				|| request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

			// use jackson to deserialize json
			ObjectMapper mapper = new ObjectMapper();
			UsernamePasswordAuthenticationToken authRequest = null;
			try (InputStream is = request.getInputStream()) {
				AuthenticationBean authenticationBean = mapper.readValue(is, AuthenticationBean.class);
				authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.getUsername(),
						authenticationBean.getPassword());
			} catch (IOException e) {
				e.printStackTrace();
				authRequest = new UsernamePasswordAuthenticationToken("", "");
			} finally {
				setDetails(request, authRequest);
			}
			return this.getAuthenticationManager().authenticate(authRequest);
		}

		// transmit it to UsernamePasswordAuthenticationFilter
		else {
			return super.attemptAuthentication(request, response);
		}
	}
}