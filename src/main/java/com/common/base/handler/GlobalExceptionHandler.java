package com.common.base.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.common.base.entity.Result;
import com.common.base.exception.RunException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RunException.class)
	public Result<? extends Object> RunExceptionHandler(HttpServletResponse response, RunException e) {

		log.info("GlobalExceptionHandler...");
		log.info("错误代码：" + response.getStatus());
		log.info(e.getMessage());
		e.printStackTrace();
		return Result.FAIL(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public Result<? extends Object> ExceptionHandler(HttpServletResponse response, Exception e) {

		log.info("GlobalExceptionHandler...");
		log.info("错误代码：" + response.getStatus());
		log.info(e.getMessage());
		e.printStackTrace();
		return Result.FAIL(e.getMessage());
	}

	/**
	 * 参数绑定异常拦截 一般是post传参会调用的jackson转换异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<? extends Object> handleException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		// 获取参数绑定结果
		BindingResult bindingResult = e.getBindingResult();
		Map<String, String> map = new HashMap<String, String>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage()); // 将错误信息放入map
		}
		return Result.FAIL("参数错误"+map, map);
	}

	/**
	 * 参数绑定异常拦截 一般是get传参参数映射异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public Result<? extends Object> handleException(BindException e) {
		log.error(e.getMessage(), e);
		// 获取参数绑定结果
		BindingResult bindingResult = e.getBindingResult();
		Map<String, String> map = new HashMap<String, String>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return Result.FAIL("参数错误"+map, map);
	}

	/**
	 * 参数获取异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Result<? extends Object> handleException(MissingServletRequestParameterException e) {
		log.error(e.getMessage(), e);
		return Result.FAIL(e.getMessage());
	}
}