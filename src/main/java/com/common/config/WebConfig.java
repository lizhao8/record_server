package com.common.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootConfiguration
public class WebConfig {

	/*@Bean
	public ApiInterceptor interceptor() {
		return new ApiInterceptor();
	}*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
	 * @Bean public LoginInterceptor logInterceptor() { return new
	 * LoginInterceptor(); }
	 * 
	 * 
	 *   @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");   
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
	 * 
	 */
	/*@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {

		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.xx.xx.xx.mapper");// 普通mapper的位置

		Properties properties = new Properties();
		properties.setProperty("mappers", BaseMapper.class.getName());// 通用mapper的全名
		properties.setProperty("notEmpty", "false");
		properties.setProperty("IDENTITY", "MYSQL");// 配置数据库方言

		mapperScannerConfigurer.setProperties(properties);

		return mapperScannerConfigurer;
	}
*/
	/**
	 * 配置mybatis的分页插件pageHelper
	 */
	/*@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		// 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
		properties.setProperty("offsetAsPageNum", "true");
		// 置为true时，使用RowBounds分页会进行count查询
		properties.setProperty("rowBoundsWithCount", "true");
		// 合理化查询,启用合理化时，
		// 如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
		// 未开启时如果pageNum<1或pageNum>pages会返回空数据
		properties.setProperty("reasonable", "true");
		// 配置mysql数据库的方言
		properties.setProperty("dialect", "mysql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}*/

}