package com.mry.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 全局异常捕捉处理
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Map<Object, Object> errorHandler(Exception exception) {
		Map<Object, Object> error = new HashMap<Object, Object>();
		error.put("code", 500);
		error.put("message", exception.getMessage());
		logger.error("Exception: " + exception.getMessage());
		return error;
	}

	// 自定义异常处理
	@ExceptionHandler(value = CommonException.class)
	@ResponseBody
	public Map<Object, Object> myErrorHandler(CommonException exception) {
		Map<Object, Object> error = new HashMap<Object, Object>();
		error.put("code", exception.getCode());
		error.put("message", exception.getMessage());
		logger.error("Exception: " + exception.getMessage());
		return error;
	}
}
