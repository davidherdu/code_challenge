package com.github.davidherdu.code_challenge;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.github.davidherdu.code_challenge.beans.OrderResult;
import com.github.davidherdu.code_challenge.beans.Shipment;
import com.github.davidherdu.code_challenge.beans.Tracking;

@Service
@ConfigurationProperties(prefix = "unit-test")
public class TestMocks {

	private Shipment shipment;
	private Tracking tracking1;
	private Tracking tracking2;
	private Tracking tracking3;
	private Tracking tracking4;
	private Tracking tracking5;
	private Tracking tracking6;
	private Tracking tracking7;
	private Tracking tracking8;
	private Tracking tracking9;
	private OrderResult result;
	

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Tracking getTracking1() {
		return tracking1;
	}

	public void setTracking1(Tracking tracking1) {
		this.tracking1 = tracking1;
	}

	public Tracking getTracking2() {
		return tracking2;
	}

	public void setTracking2(Tracking tracking2) {
		this.tracking2 = tracking2;
	}

	public Tracking getTracking3() {
		return tracking3;
	}

	public void setTracking3(Tracking tracking3) {
		this.tracking3 = tracking3;
	}

	public Tracking getTracking4() {
		return tracking4;
	}

	public void setTracking4(Tracking tracking4) {
		this.tracking4 = tracking4;
	}

	public Tracking getTracking5() {
		return tracking5;
	}

	public void setTracking5(Tracking tracking5) {
		this.tracking5 = tracking5;
	}

	public Tracking getTracking6() {
		return tracking6;
	}

	public void setTracking6(Tracking tracking6) {
		this.tracking6 = tracking6;
	}

	public Tracking getTracking7() {
		return tracking7;
	}

	public void setTracking7(Tracking tracking7) {
		this.tracking7 = tracking7;
	}

	public Tracking getTracking8() {
		return tracking8;
	}

	public void setTracking8(Tracking tracking8) {
		this.tracking8 = tracking8;
	}

	public Tracking getTracking9() {
		return tracking9;
	}

	public void setTracking9(Tracking tracking9) {
		this.tracking9 = tracking9;
	}

	public OrderResult getResult() {
		return result;
	}

	public void setResult(OrderResult result) {
		this.result = result;
	}
}
