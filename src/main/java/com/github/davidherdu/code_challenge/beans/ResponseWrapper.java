package com.github.davidherdu.code_challenge.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseWrapper implements Serializable {

	private static final long serialVersionUID = -606951509446712098L;
	
	@JsonInclude(Include.NON_NULL)
	private String message;
    @JsonInclude(Include.NON_NULL)
	private String code;
    @JsonInclude(Include.NON_NULL)
	private String type;

	public ResponseWrapper() {
		super();
	}

	public ResponseWrapper(String code, String message, String type) {
		super();
		this.code = code;
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
