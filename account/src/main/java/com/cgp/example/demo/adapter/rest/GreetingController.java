package com.cgp.example.demo.adapter.rest;

import com.cgp.example.demo.application.port.in.GreetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST adapter for greeting operations.
 * This controller handles HTTP requests and delegates to the application service.
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class GreetingController {

	private final GreetingService greetingService;

	/**
	 * GET /greeting endpoint.
	 *
	 * @return the greeting message
	 */
	@GetMapping("/greeting")
	public String greeting() {
		log.info("Received request for /greeting endpoint");
		String message = greetingService.getGreeting();
		log.debug("Returning greeting message: {}", message);
		return message;
	}
}
