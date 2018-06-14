package com.github.davidherdu.code_challenge;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.github.davidherdu.code_challenge.beans.Shipment;
import com.github.davidherdu.code_challenge.beans.Tracking;
import com.github.davidherdu.code_challenge.constants.Constants;
import com.github.davidherdu.code_challenge.controller.OrdersController;
import com.github.davidherdu.code_challenge.service.OrdersService;
import com.github.davidherdu.code_challenge.util.Utils;
import com.github.davidherdu.code_challenge.event.CodeChallengeEvent;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	private OrdersController ordersController;
	@MockBean
	private OrdersService ordersServiceMock;
	@MockBean
	private AbstractApplicationContext publisher;
	@Autowired
	private TestMocks testMocks;
	
	@Before
	public void setup() {	
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
	}
	
	@Test
	public void registerTest() {
		doNothing().when(ordersServiceMock).setShipment(any(Shipment.class));
		
		ordersController.register(testMocks.getShipment());
		verify(ordersServiceMock, times(1)).setShipment(testMocks.getShipment());
	}
	
	@Test
	public void register_200_Test() throws Exception {
		Shipment shipment = testMocks.getShipment();
		
		doNothing().when(ordersServiceMock).setShipment(shipment);
	
		mockMvc.perform(post(Constants.REST_ROOT_API.concat(Constants.REST_REGISTER_API))
						.contentType(MediaType.APPLICATION_JSON)
						.content(Utils.asJsonString(shipment)))
				.andExpect(status().isOk());
		
		verify(ordersServiceMock, times(1)).setShipment(shipment);
	}
	
	@Test
	public void pushTest() {
		doCallRealMethod().when(publisher).publishEvent(new CodeChallengeEvent(this, testMocks.getResult()));
		
		ordersController.push(testMocks.getTracking6());
		verify(ordersServiceMock, times(1)).calculateStatus(testMocks.getTracking6());
	}
	
	@Test
	public void push_200_Test() throws Exception {
		Tracking tracking = testMocks.getTracking6();
		 
		doCallRealMethod().when(publisher).publishEvent(new CodeChallengeEvent(this, testMocks.getResult()));
		
		mockMvc.perform(put(Constants.REST_ROOT_API.concat(Constants.REST_PUSH_API))
						.contentType(MediaType.APPLICATION_JSON)
						.content(Utils.asJsonString(tracking)))
				.andExpect(status().isOk());

		verify(ordersServiceMock, times(1)).calculateStatus(tracking);
	}
	
	@Test
	public void push_404_Test() throws Exception {		
		mockMvc.perform(put(Constants.REST_PUSH_API)
						.contentType(MediaType.APPLICATION_JSON)
						.content(Utils.asJsonString(testMocks.getTracking1())))
				.andExpect(status().isNotFound());
	}
}
