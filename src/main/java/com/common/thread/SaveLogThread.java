package com.common.thread;

import java.lang.reflect.Method;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.method.HandlerMethod;

import com.common.entity.Log;
import com.common.service.LogService;
import com.common.util.StringUtils;

/**
 * 开启保存日志线程
 */
public class SaveLogThread extends Thread {

	// 工具类中的service注入不能用注解方式 所以用spring方式载入
	private static LogService logService = (LogService) ContextLoader
			.getCurrentWebApplicationContext().getBean("logService");

	private Log log;
	private Object handler;
	private Exception ex;

	public SaveLogThread(Log log, Object handler, Exception ex) {
		super(SaveLogThread.class.getSimpleName());
		this.log = log;
		this.handler = handler;
		this.ex = ex;
	}

	@Override
	public void run() {
		// 获取日志标题
		if (StringUtils.isNull(log.getTitle())) {
			String title = null;
			if (handler instanceof HandlerMethod) {
				Method m = ((HandlerMethod) handler).getMethod();
				String name = m.getName();
				Class clazz = m.getDeclaringClass();
				if (clazz != null && name != null) {
					title = clazz + "---" + name;
				} else {
					title = "";
				}
			}
			log.setTitle(title);
		}
		// 如果有异常，设置异常信息
		// 如果无标题并无异常日志，则不保存信息
		if (StringUtils.isNotNull(log.getTitle())) {
			logService.save(log);
		}
		// 保存日志信息
	}
}