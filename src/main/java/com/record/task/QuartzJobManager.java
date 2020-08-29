package com.record.task;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

@Component
public class QuartzJobManager {
	@Autowired
	private Scheduler scheduler;

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/** 默认任务组名称 */
	private static final String DEFAULT_JOB_GROUP_NAME = "DefaultJobGroup";
	/** 默认触发器组名称 */
	private static final String DEFAULT_TRIGGER_GROUP_NAME = "DefaultTriggerGroup";

	public void addJobBeanMethod(String jobName, String triggerName, String targetBeanName, String targetMethod,
			String cron) {
		Object targetObject = ContextLoader.getCurrentWebApplicationContext().getBean(targetBeanName);
		addJobObjectMethod(jobName, triggerName, targetObject, targetMethod, cron);
	}

	public void addJobObjectMethod(String jobName, String triggerName, Object targetObject, String targetMethod,
			String cron) {
		addJob(jobName, triggerName, null, targetObject, targetMethod, cron);
	}

	public void addJobClassMethod(String jobName, String triggerName, String targetClassFullName, String targetMethod,
			String cron) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> targetClass = Class.forName(targetClassFullName);
		addJobClassMethod(jobName, triggerName, targetClass, targetMethod, cron);
	}

	public void addJobClassMethod(String jobName, String triggerName, Class<?> targetClass, String targetMethod,
			String cron) throws InstantiationException, IllegalAccessException {
		try {
			Method method = targetClass.getDeclaredMethod(targetMethod);
			if (Modifier.isStatic(method.getModifiers())) {
				addJob(jobName, triggerName, targetClass, null, targetMethod, cron);
			} else {
				addJob(jobName, triggerName, targetClass, targetClass.newInstance(), targetMethod, cron);
			}
		} catch (NoSuchMethodException | SecurityException e) {
			addJob(jobName, triggerName, targetClass, targetClass.newInstance(), targetMethod, cron);
		}
	}

	public void addJob(String jobName, String triggerName, Class<?> targetClass, Object targetObject,
			String targetMethod, String cron) {
		try {
			MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
			methodInvokingJobDetailFactoryBean.setConcurrent(false); // 是否并发
			methodInvokingJobDetailFactoryBean.setGroup(DEFAULT_JOB_GROUP_NAME); // 任务组名
			methodInvokingJobDetailFactoryBean.setName(jobName); // 任务名
			methodInvokingJobDetailFactoryBean.setTargetClass(targetClass); // 目标类
			methodInvokingJobDetailFactoryBean.setTargetObject(targetObject); // 目标bean
			methodInvokingJobDetailFactoryBean.setTargetMethod(targetMethod); // 目标类的方法
			methodInvokingJobDetailFactoryBean.afterPropertiesSet(); // 生成jobDetail

			JobDetail jobDetail = methodInvokingJobDetailFactoryBean.getObject();
			// 触发器
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			// 触发器名,触发器组
			triggerBuilder.withIdentity(triggerName, DEFAULT_TRIGGER_GROUP_NAME);
			triggerBuilder.startNow();
			// 触发器时间设定
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
			// 创建Trigger对象
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();

			// 调度容器设置JobDetail和Trigger
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 添加一个定时任务
	 * 
	 * @param jobName          任务名
	 * @param jobGroupName     任务组名
	 * @param triggerName      触发器名
	 * @param triggerGroupName 触发器组名
	 * @param jobClass         任务
	 * @param cron             时间设置，参考quartz说明文档
	 */
	public void addJob(String jobName, String triggerName, Class<? extends Job> jobClass, String cron) {
		try {
			// 任务名，任务组，任务执行类
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, DEFAULT_JOB_GROUP_NAME).build();

			// 触发器
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			// 触发器名,触发器组
			triggerBuilder.withIdentity(triggerName, DEFAULT_TRIGGER_GROUP_NAME);
			triggerBuilder.startNow();
			// 触发器时间设定
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
			// 创建Trigger对象
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();

			// 调度容器设置JobDetail和Trigger
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName      触发器名
	 * @param triggerGroupName 触发器组名
	 * @param cron             时间设置，参考quartz说明文档
	 */
	public void modifyJobTime(String jobName, String triggerName, String cron) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, DEFAULT_TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}

			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				/** 方式一 ：调用 rescheduleJob 开始 */
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName, DEFAULT_TRIGGER_GROUP_NAME);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
				// 创建Trigger对象
				trigger = (CronTrigger) triggerBuilder.build();
				// 方式一 ：修改一个任务的触发时间
				scheduler.rescheduleJob(triggerKey, trigger);
				/** 方式一 ：调用 rescheduleJob 结束 */

				/** 方式二：先删除，然后在创建一个新的Job */
				// JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName,
				// jobGroupName));
				// Class<? extends Job> jobClass = jobDetail.getJobClass();
				// removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
				// addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
				/** 方式二 ：先删除，然后在创建一个新的Job */
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 */
	public void removeJob(String jobName, String triggerName) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, DEFAULT_TRIGGER_GROUP_NAME);

			scheduler.pauseTrigger(triggerKey);// 停止触发器
			scheduler.unscheduleJob(triggerKey);// 移除触发器
			scheduler.deleteJob(JobKey.jobKey(jobName, DEFAULT_JOB_GROUP_NAME));// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:启动所有定时任务
	 */
	public void startJobs() {
		try {
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:关闭所有定时任务
	 */
	public void shutdownJobs() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void dododo() {
		System.out.println(new Date() + ": jobQ 1 doing something...begin");
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new Date() + ": jobQ 1 doing something...end");

	}

}
