package com.springboot.mvc.exception;

public class SpringBootMVCException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errCode;
	private String message;

	public SpringBootMVCException() {
		super();
	}

	public SpringBootMVCException(String message) {
		super(message);
		this.message = message;
	}

	public SpringBootMVCException(int errCode, String message) {
		super(message);
		this.errCode = errCode;
		this.message = message;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
