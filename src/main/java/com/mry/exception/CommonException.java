package com.mry.exception;

public class CommonException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String message;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CommonException(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
