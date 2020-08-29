package com.common.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 * 
 * @author LIZHAO
 * @date 2020年7月30日
 * @desc 描述
 */
@SpringBootConfiguration
public class CorsConfig {
	@Bean
	public CorsFilter corsFilter() {
		// 1.添加CORS配置信息
		CorsConfiguration config = new CorsConfiguration();
		// 1) 允许的域,不要写*，否则cookie就无法使用了
		config.addAllowedOrigin("*");
		// 2) 是否发送Cookie信息
		config.setAllowCredentials(true);
		// 3) 允许的请求方式
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");

		// config.addAllowedMethod("HEAD");
		// config.addAllowedMethod("PUT");
		// config.addAllowedMethod("DELETE");
		// config.addAllowedMethod("PATCH");
		// 4）允许的头信息
		config.addAllowedHeader("*");

		// 2.添加映射路径，我们拦截一切请求
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		configSource.registerCorsConfiguration("/**", config);

		// 3.返回新的CorsFilter.
		return new CorsFilter(configSource);
	}
}