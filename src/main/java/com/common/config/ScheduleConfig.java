package com.common.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer, AsyncConfigurer {

	// 并行任务
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		TaskScheduler taskScheduler = taskScheduler();
		taskRegistrar.setTaskScheduler(taskScheduler);
	}

	// 并行任务使用策略：多线程处理（配置线程数等）
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setThreadNamePrefix("task-"); // 设置线程名开头
		scheduler.setAwaitTerminationSeconds(60);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}

	// 异步任务
	@Override
	public Executor getAsyncExecutor() {
		Executor executor = taskScheduler();
		return executor;
	}

	// 异步任务 异常处理
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}