package com.github.davidherdu.code_challenge.strategies;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.davidherdu.code_challenge.beans.Status;

@Component
public class StrategyFactory {

	private Map<Status, OrdersStrategy> strategies = new EnumMap<>(Status.class);
	
	public StrategyFactory() {
		super();
		initFactory();
	}

	/**
	 * Method to get a strategy depending of status
	 * @param status
	 * @return
	 */
	public OrdersStrategy getStrategy(Status status) {
		if (status == null || !strategies.containsKey(status)) {
			throw new IllegalArgumentException("Strategy " + status + " not found");
		}
		return this.strategies.get(status);
	}

	private void initFactory() {
		strategies.put(Status.CONCILLIATION_REQUEST, new ConciliationOrderStrategy());
		strategies.put(Status.NOT_NEEDED, new NotNeededOrderStrategy());
		strategies.put(Status.INCOMPLETE, new IncompleteOrderStrategy());
		strategies.put(Status.NOT_FOUND, new NotFoundOrderStrategy());
	}
}
