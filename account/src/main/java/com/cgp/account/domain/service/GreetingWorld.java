package com.cgp.account.domain.service;

import org.springframework.stereotype.Service;

/**
 * Domain service for greeting world operations.
 * This service contains core domain logic related to world greetings.
 */
@Service
public class GreetingWorld {

	/**
	 * Returns the greeting.
	 *
	 * @return the greeting
	 */
	public String getGreeting() {
		return "World";
	}
}
