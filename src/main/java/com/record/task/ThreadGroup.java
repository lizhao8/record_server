package com.record.task;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadGroup<T extends Thread> extends java.lang.ThreadGroup {

	public ThreadGroup(String name) {
		super(name);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable e) {
		super.uncaughtException(thread, e);
		log.error(thread.getName() + "==" + e.getMessage());
	}

	public Map<String, Thread> getThreadMap() {
		List<Thread> list = getThreadList();
		Map<String, Thread> map = new HashMap<String, Thread>();
		for (Thread thread : list) {
			try {
				map.put(thread.getName(), thread);
			} catch (Exception e) {
				log.info("线程不见了");
			}
		}
		return map;
	}

	public List<Thread> getThreadList() {
		int activeCount = this.activeCount();
		Thread[] lstThreads = new Thread[activeCount];
		this.enumerate(lstThreads);
		return Arrays.asList(lstThreads);
	}

	public T getThread(String threadName) {
		Map<String, Thread> map = getThreadMap();
		return (T)map.get(threadName);
	}

}
