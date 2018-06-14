package com.github.davidherdu.code_challenge.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.github.davidherdu.code_challenge.util.Utils;

@Component
public class CodeChallengeEventListener implements ApplicationListener<CodeChallengeEvent> {
	
	/**
	 * This method show by console application event
	 */
	@Override
	public void onApplicationEvent(CodeChallengeEvent event) {
		System.out.println("Application event\n" + Utils.asJsonString(event.getOrderResult()));	
	}
}
