package com.cgp.account.application.service;

import com.cgp.account.application.port.in.GreetingService;
import com.cgp.account.domain.service.GreetingWorld;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the GreetingService port.
 * This service contains the business logic for greeting operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

	private final GreetingWorld greetingWorld;

	@Override
	public String getGreeting() {
		log.debug("Generating greeting message");
		String greeting = greetingWorld.getGreeting();
		return "Hello, " + greeting;
	}
}
