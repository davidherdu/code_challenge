package com.github.davidherdu.code_challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.davidherdu.code_challenge.beans.OrderResult;
import com.github.davidherdu.code_challenge.beans.Shipment;
import com.github.davidherdu.code_challenge.beans.Status;
import com.github.davidherdu.code_challenge.service.OrdersService;
import com.github.davidherdu.code_challenge.util.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PacklinkApplicationTests {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private TestMocks testMocks;
	
	@Before
	public void contextLoads() {
		ordersService.setShipment(testMocks.getShipment());
	}
	
	@Test
	public void hashNullPropertiesTest() {
		assertTrue(Utils.hashNullProperties(new OrderResult(null, Status.CONCILLIATION_REQUEST)));
		assertTrue(Utils.hashNullProperties(new OrderResult("ABCD123456", null)));
		assertFalse(Utils.hashNullProperties(new OrderResult("ABCD123456", Status.CONCILLIATION_REQUEST)));
	}
	
	@Test
	public void setShipmentTest() {
		OrdersService serviceMock = mock(OrdersService.class);
		doNothing().when(serviceMock).setShipment(any());
		serviceMock.setShipment(testMocks.getShipment());
		
		verify(serviceMock, times(1)).setShipment(testMocks.getShipment());
	}
	
	@Test
	public void setShipmentTestComplex() {
		OrdersService serviceMock = mock(OrdersService.class);
	    doAnswer((Answer<Void>) invocation -> {
	        Object arg0 = invocation.getArgumentAt(0, Shipment.class);
	         
	        assertEquals(testMocks.getShipment(), arg0);
	        return null;
	    }).when(serviceMock).setShipment(any(Shipment.class));
	    serviceMock.setShipment(testMocks.getShipment());
	}
	
	@Test
	public void calculateIncompleteStateTest() {
		OrderResult result1 = ordersService.calculateStatus(testMocks.getTracking1());
		OrderResult result2 = ordersService.calculateStatus(testMocks.getTracking2());
		OrderResult result3 = ordersService.calculateStatus(testMocks.getTracking3());
		OrderResult result4 = ordersService.calculateStatus(testMocks.getTracking4());
		OrderResult result5 = ordersService.calculateStatus(testMocks.getTracking8());
		
		assertTrue(result1.getStatus().equals(Status.INCOMPLETE));
		assertTrue(result2.getStatus().equals(Status.INCOMPLETE));
		assertTrue(result3.getStatus().equals(Status.INCOMPLETE));
		assertTrue(result4.getStatus().equals(Status.INCOMPLETE));
		assertTrue(result5.getStatus().equals(Status.INCOMPLETE));
	}
	
	@Test
	public void calculateNotNeededStateTest() {
		OrderResult result1 = ordersService.calculateStatus(testMocks.getTracking5());
		
		assertTrue(result1.getStatus().equals(Status.NOT_NEEDED));
	}
	
	@Test
	public void calculateConciliationStateTest() {
		OrderResult result = ordersService.calculateStatus(testMocks.getTracking6());
		
		assertTrue(result.getStatus().equals(Status.CONCILLIATION_REQUEST));
	}
	
	@Test
	public void calculateNotFoundStateTest() {
		OrderResult result1 = ordersService.calculateStatus(testMocks.getTracking7());
		OrderResult result2 = ordersService.calculateStatus(testMocks.getTracking9());
		
		assertTrue(result1.getStatus().equals(Status.NOT_FOUND));
		assertTrue(result2.getStatus().equals(Status.NOT_FOUND));
	}
	
	@Test
	public void errorCalculateIncompleteStateTest() {
		OrderResult result1 = ordersService.calculateStatus(testMocks.getTracking1());
		OrderResult result2 = ordersService.calculateStatus(testMocks.getTracking2());
		OrderResult result3 = ordersService.calculateStatus(testMocks.getTracking3());
		
		assertFalse(result1.getStatus().equals(Status.NOT_FOUND));
		assertFalse(result2.getStatus().equals(Status.NOT_NEEDED));
		assertFalse(result3.getStatus().equals(Status.CONCILLIATION_REQUEST));
	}
}
