package com.github.davidherdu.code_challenge.strategies;

import com.github.davidherdu.code_challenge.beans.OrderResult;
import com.github.davidherdu.code_challenge.beans.Tracking;

public interface OrdersStrategy {

	public OrderResult calculateStatus(Tracking tracking);
}
