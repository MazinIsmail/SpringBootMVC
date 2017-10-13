package com.springboot.mvc.exception;

import org.springframework.http.HttpStatus;

public class SpringBootMVCException extends Exception {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private int errCode;
	private String message;

	public SpringBootMVCException() {
		super();
	}

	public SpringBootMVCException(String message) {
		super(message);
		this.message = message;
	}

	public SpringBootMVCException(int errCode, String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
		this.message = message;
	}

	public SpringBootMVCException(HttpStatus status, int errCode, String message) {
		this.errCode = errCode;
		this.message = message;
		this.status = status;
	}

	public SpringBootMVCException(int errCode, String message) {
		super(message);
		this.errCode = errCode;
		this.message = message;
	}

	public SpringBootMVCException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public SpringBootMVCException(Throwable cause) {
		super(cause);
	}

	public SpringBootMVCException(int errCode, String message, HttpStatus status) {
		this.errCode = errCode;
		this.message = message;
		this.status = status;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
