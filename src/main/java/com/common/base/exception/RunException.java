package com.common.base.exception;

/**
 * 自定义异常
 * 
 * @author Administrator
 *
 */
public class RunException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RunException(Exception e) {
		super(e);
	}

	public RunException(String message, Throwable cause) {
		super(message, cause);
	}

	public RunException(String message) {
		super(message);
	}

}
