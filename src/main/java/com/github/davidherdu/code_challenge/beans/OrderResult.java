package com.github.davidherdu.code_challenge.beans;

import java.io.Serializable;

public class OrderResult implements Serializable {

	private static final long serialVersionUID = -6893573732695265099L;

	private String reference;
	private Status status;
	
	public OrderResult() {
		super();
	}

	public OrderResult(String reference, Status status) {
		super();
		this.reference = reference;
		this.status = status;
	}

	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}
