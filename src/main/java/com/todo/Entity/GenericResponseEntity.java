package com.todo.Entity;

public class GenericResponseEntity {
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

	public GenericResponseEntity(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
