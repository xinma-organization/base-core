package com.xinma.base.core.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.xinma.base.core.error.CustomError;

/**
 * 系统自定义异常，继承自RuntimeException
 * 
 * @author Alauda
 *
 * @date 2016年5月19日
 *
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -4713824450047222637L;

	/**
	 * 系统错误代码
	 */
	private CustomError error;

	/**
	 * 系统错误参数
	 */
	private String[] params;

	public CustomError getError() {
		return error;
	}

	public void setError(CustomError error) {
		this.error = error;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public CustomException() {
		super();
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 *            异常描述信息
	 * @param error
	 *            错误代码
	 * @param params
	 *            错误信息需要的参数
	 */
	public CustomException(String message, CustomError error, String... params) {
		super(message);
		this.error = error;
		this.params = params;
	}

	/**
	 * 构造函数
	 * 
	 * @param error
	 *            CustomError对象
	 * @param params
	 *            错误信息需要的参数列表
	 */
	public CustomException(CustomError error, String... params) {
		super(error.description());
		this.error = error;
		this.params = params;
	}

	/**
	 * 构造函数
	 * 
	 * @param cause
	 *            Throwable Object
	 * @param error
	 *            CustomError对象
	 * @param params
	 *            错误信息需要的参数列表
	 */
	public CustomException(Throwable cause, CustomError error, String... params) {
		super(error.description(), cause);
		this.error = error;
		this.params = params;
	}

	/**
	 * 获取Exception stack trace 信息
	 * 
	 * @return stack trace
	 */
	public String getExceptionStackTrace() {
		StringWriter sw = new StringWriter();
		this.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	/**
	 * 获取Exception stack trace 信息
	 * 
	 * @param ex
	 * @return
	 */
	public static String getExceptionStackTrace(Throwable ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}