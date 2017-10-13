package com.springboot.mvc.domain;

public class ErrorMessage {

	private int errCode;
	private String message;

	public ErrorMessage(int errCode, String message) {
		super();
		this.errCode = errCode;
		this.message = message;
	}

	public ErrorMessage() {
		super();
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
