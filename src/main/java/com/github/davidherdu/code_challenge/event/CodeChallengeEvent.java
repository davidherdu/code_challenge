package com.github.davidherdu.code_challenge.event;

import org.springframework.context.ApplicationEvent;

import com.github.davidherdu.code_challenge.beans.OrderResult;

public class CodeChallengeEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3453505786051952494L;

	private OrderResult orderResult;
	
	public CodeChallengeEvent(Object source, OrderResult orderResult) {
		super(source);
		this.orderResult = orderResult;
	}

	public OrderResult getOrderResult() {
		return orderResult;
	}
}
