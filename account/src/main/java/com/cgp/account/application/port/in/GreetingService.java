package com.cgp.account.application.port.in;

/**
 * Port for greeting operations.
 * This is part of the hexagonal architecture's inbound port.
 */
public interface GreetingService {

	/**
	 * Returns a greeting message.
	 *
	 * @return the greeting message
	 */
	String getGreeting();
}
