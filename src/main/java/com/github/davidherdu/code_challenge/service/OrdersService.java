package com.github.davidherdu.code_challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.davidherdu.code_challenge.beans.OrderResult;
import com.github.davidherdu.code_challenge.beans.Shipment;
import com.github.davidherdu.code_challenge.beans.Status;
import com.github.davidherdu.code_challenge.beans.Tracking;
import com.github.davidherdu.code_challenge.exception.CodeChallengeException;
import com.github.davidherdu.code_challenge.strategies.OrdersStrategy;
import com.github.davidherdu.code_challenge.strategies.StrategyFactory;
import com.github.davidherdu.code_challenge.util.Utils;

@Service
public class OrdersService {

	@Autowired
	private StrategyFactory strategyFactory;

	private Shipment shipment;

	/**
	 * The shipment to application is set. If any field is null a CodeChallengeException is throwed y it is catched in CodeChallengeExceptionChecker
	 * @param shipment
	 */
	public void setShipment(Shipment shipment) {
		if (Utils.hashNullProperties(shipment)) {
			throw new CodeChallengeException("Shipment can't have any field null");
		}
		this.shipment = shipment;
	}

	/**
	 * Method to calculate status of orders. I he developed strategy pattern to get result depending of calculation
	 * @param tracking
	 * @return result to calculation
	 */
	public OrderResult calculateStatus(Tracking tracking) {
		OrdersStrategy strategy = strategyFactory.getStrategy(Status.NOT_FOUND);
		if (this.shipment != null && this.shipment.getReference().equals(tracking.getReference())) {
			if (Utils.hashNullProperties(tracking) || !tracking.getStatus().equals(Status.DELIVERED)) {
				strategy = strategyFactory.getStrategy(Status.INCOMPLETE);
			} else if (this.shipment.getParcels().size() == tracking.getParcels()) {
				int totalWeight = this.shipment.getParcels().stream().mapToInt(s -> s.getWeight()).sum();

				strategy = totalWeight < tracking.getWeight()
						? strategyFactory.getStrategy(Status.CONCILLIATION_REQUEST)
						: strategyFactory.getStrategy(Status.NOT_NEEDED);
			}
		}

		return strategy.calculateStatus(tracking);
	}
}
