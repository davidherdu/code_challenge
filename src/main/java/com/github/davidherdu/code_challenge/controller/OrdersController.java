package com.github.davidherdu.code_challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.davidherdu.code_challenge.beans.OrderResult;
import com.github.davidherdu.code_challenge.beans.Shipment;
import com.github.davidherdu.code_challenge.beans.Tracking;
import com.github.davidherdu.code_challenge.constants.Constants;
import com.github.davidherdu.code_challenge.event.CodeChallengeEvent;
import com.github.davidherdu.code_challenge.service.OrdersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Constants.REST_ROOT_API)
@Api(value = Constants.ORDERS_API)
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;

	/**
	 * Method to set a shipment to app
	 * @param shipment
	 */
	@ApiOperation(value = Constants.ORDERS_API_REGISTER)
	@PostMapping(path = Constants.REST_REGISTER_API)
	public void register(@RequestBody Shipment shipment) {
		ordersService.setShipment(shipment);
	}

	/**
	 * Method to set a tracking to calculation and dispatch an application event with result of calculation
	 * @param tracking
	 */
	@ApiOperation(value = Constants.ORDERS_API_PUSH)
	@PutMapping(path = Constants.REST_PUSH_API)
	public void push(@RequestBody Tracking tracking) {
		OrderResult result = ordersService.calculateStatus(tracking);
		applicationEventPublisher.publishEvent(new CodeChallengeEvent(this, result));
	}
}
