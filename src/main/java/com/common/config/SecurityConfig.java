package com.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.common.config.porvider.CustomAuthenticationFilter;
import com.common.config.porvider.SelfAuthenticationProvider;
import com.common.config.porvider.SelfAuthenticationSuccessHandler;
import com.common.config.porvider.SelfLoginUrlAuthenticationEntryPoint;
import com.common.config.porvider.SelfLogoutSuccessHandler;
import com.common.config.porvider.SelflAuthenticationFailureHandler;
import com.common.config.porvider.TokenAuthenticationFilter;

@SpringBootConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 自定义登录认证
	 */
	@Autowired
	private SelfAuthenticationProvider authenticationProvider;

	/**
	 * 自定义登录成功处理器
	 */
	@Autowired
	private SelfAuthenticationSuccessHandler authenticationSuccessHandler;

	/**
	 * 自定义登录失败处理器
	 */
	@Autowired
	private SelflAuthenticationFailureHandler authenticationFailureHandler;

	/**
	 * 自定义注销处理器
	 */
	@Autowired
	private SelfLogoutSuccessHandler logoutSuccessHandler;

	/**
	 * 登录认证
	 * 
	 * @param auth 登陆管理器
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		// 添加自定义登陆认证
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题		
		web.ignoring().antMatchers("/static/**");
		web.ignoring().antMatchers("/");
    }
	
	/**
	 * 具体配置登陆细节
	 * 
	 * @param http 登陆访问对象
	 * @throws Exception 登陆异常
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 关闭csrf
		http.csrf().disable().cors().and()
				// 关闭Session,让 Spring Security 不创建和使用 session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// 开放api路径
				.and().authorizeRequests().antMatchers("/api/**").permitAll().anyRequest().authenticated()
				//.and().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated()
				// 自定义登录请求路径(post请求)
				.and().formLogin().loginProcessingUrl("/login")
				// 自定义用户名名称
				.usernameParameter("username")
				// 自定义密码名称
				.passwordParameter("password")
				// 验证成功处理器
				.successHandler(authenticationSuccessHandler)
				// 验证失败处理器
				.failureHandler(authenticationFailureHandler)//.permitAll()
				// 关闭拦截未登录自动跳转,改为返回json信息
				.and().exceptionHandling().authenticationEntryPoint(selfLoginUrlAuthenticationEntryPoint())
				// 开启自动配置的注销功能
				.and().logout().logoutUrl("/logout")
				// 注销成功处理器
				.logoutSuccessHandler(logoutSuccessHandler)//.permitAll()
				// 添加token过滤器
				.and().addFilterBefore(new TokenAuthenticationFilter(authenticationManagerBean()),
						UsernamePasswordAuthenticationFilter.class);

		// 用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
		http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

//注册自定义的UsernamePasswordAuthenticationFilter
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(authenticationFailureHandler);
		filter.setFilterProcessesUrl("/login");
		// 这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
		filter.setAuthenticationManager(authenticationManagerBean());
		return filter;
	}

	/**
	 * 身份认证失败处理类
	 * 
	 * @return AuthenticationEntryPoint
	 */
	@Bean
	public AuthenticationEntryPoint selfLoginUrlAuthenticationEntryPoint() {
		return new SelfLoginUrlAuthenticationEntryPoint("/");
	}

	/**
	 * 重写方法，是上下文可以获取本地缓存对象
	 * 
	 * @return AuthenticationManager 本地缓存对象
	 * @throws Exception 异常
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}