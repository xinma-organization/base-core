package com.xinma.base.core.exceptions;

import com.xinma.base.core.error.CustomError;

/**
 * 系统业务逻辑自定义异常
 * 
 * @author Alauda
 *
 * @date 2016年5月19日
 *
 */
public class ServiceException extends CustomException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -631485300951305336L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(CustomError error, String... params) {
		super(error, params);
	}

	public ServiceException(String message, CustomError error, String... params) {
		super(message, error, params);
	}

	public ServiceException(Throwable cause, CustomError error, String... params) {
		super(cause, error, params);
	}
}
