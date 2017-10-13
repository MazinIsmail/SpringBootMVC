package com.springboot.mvc.domain;

public enum ErrorCode {
	MANDATORY(10, "File is missing."), EXISTING(20, "File has been uploaded before."), INTERNALSERVERERROR(30,
			"Internal Server Error.");

	private final int code;
	private final String description;

	private ErrorCode(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + " - " + description;
	}
}
