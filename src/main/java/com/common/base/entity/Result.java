package com.common.base.entity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.common.util.JsonUtil;

import lombok.Data;

/**
 * 自定义响应结构 方法间传复杂参数使用
 */
@Data
public class Result<T> {

	/** 响应业务状态码 */
	private Integer code;

	/** 响应消息 */
	private String message;

	/** 响应中的数据 */
	private T data;

	/** 构造函数 */
	private Result() {

	}

	private Result(Integer code) {
		this.code = code;
	}

	private Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	private Result(Integer code, T data) {
		this.code = code;
		this.data = data;
	}

	private Result(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/** 成功 */
	public static <T> Result<T> SUCCESS(String message) {
		return new Result<T>(Code.SUCCESS, message);
	}

	public static <T> Result<T> SUCCESS(T data) {
		return new Result<T>(Code.SUCCESS, data);
	}

	public static <T> Result<T> SUCCESS(String message, T data) {
		return new Result<T>(Code.SUCCESS, message, data);
	}

	/** 失败 */
	public static <T> Result<T> FAIL(String message) {
		return new Result<T>(Code.FAIL, message);
	}

	public static <T> Result<T> FAIL(T data) {
		return new Result<T>(Code.FAIL, data);
	}

	public static <T> Result<T> FAIL(String message, T data) {
		return new Result<T>(Code.FAIL, message, data);
	}

	/** 构建 */
	public static <T> Result<T> BUILD(Integer state, String message) {
		return new Result<T>(state, message);
	}

	public static <T> Result<T> BUILD(Integer state, T data) {
		return new Result<T>(state, data);
	}

	public static <T> Result<T> BUILD(Integer state, String message, T data) {
		return new Result<T>(state, message, data);
	}

	/** 常用 */
	public static <T> Result<T> SUCCESS() {
		return new Result<T>(Code.SUCCESS, Message.SUCCESS);
	}

	public static <T> Result<T> FAIL() {
		return new Result<T>(Code.FAIL, Message.FAIL);
	}

	public static <T> Result<T> SAVE_SUCCESS() {
		return new Result<T>(Code.SUCCESS, Message.SAVE_SUCCESS);
	}

	public static <T> Result<T> SAVE_FAIL() {
		return new Result<T>(Code.FAIL, Message.SAVE_FAIL);
	}

	public static <T> Result<T> UPDATE_SUCCESS() {
		return new Result<T>(Code.SUCCESS, Message.UPDATE_SUCCESS);
	}

	public static <T> Result<T> UPDATE_FAIL() {
		return new Result<T>(Code.FAIL, Message.UPDATE_FAIL);
	}

	public static <T> Result<T> DELETE_SUCCESS() {
		return new Result<T>(Code.SUCCESS, Message.DELETE_SUCCESS);
	}

	public static <T> Result<T> DELETE_FAIL() {
		return new Result<T>(Code.FAIL, Message.DELETE_FAIL);
	}

	/** 判断是否成功 */
	public Boolean isSuccess() {
		return this.code == Code.SUCCESS;
	}

	@Override
	public String toString() {
		return JsonUtil.writeValueAsString(this);
	}

	public void writerToResponse(HttpServletResponse response) throws IOException {
		// Cookie token = TokenUtils.createToken(httpServletRequest);
		// response.addCookie(token);
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.setStatus(200);
		PrintWriter writer = response.getWriter();
		writer.write(toString());
		writer.flush();
		writer.close();
	}
}
