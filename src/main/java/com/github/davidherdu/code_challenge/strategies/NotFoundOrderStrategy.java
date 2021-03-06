package com.github.davidherdu.code_challenge.strategies;

import com.github.davidherdu.code_challenge.beans.OrderResult;
import com.github.davidherdu.code_challenge.beans.Status;
import com.github.davidherdu.code_challenge.beans.Tracking;

public class NotFoundOrderStrategy implements OrdersStrategy {

	@Override
	public OrderResult calculateStatus(Tracking tracking) {
		return new OrderResult(tracking.getReference(), Status.NOT_FOUND);
	}
}
