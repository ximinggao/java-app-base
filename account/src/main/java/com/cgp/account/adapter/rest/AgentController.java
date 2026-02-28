package com.cgp.account.adapter.rest;

import com.cgp.account.application.port.in.AgentService;
import com.cgp.account.application.port.in.CreateAgentRequest;
import com.cgp.account.application.port.in.CreateAgentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST adapter for agent operations.
 * This controller handles HTTP requests and delegates to the application service.
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AgentController {

	private final AgentService agentService;

	/**
	 * POST /agents endpoint to create a new agent.
	 *
	 * @param request the request body containing the agent name
	 * @return ResponseEntity with the created agent ID
	 */
	@PostMapping("/agents")
	public ResponseEntity<CreateAgentResponse> createAgent(@RequestBody CreateAgentRequest request) {
		log.info("Received request to create agent with name: {}", request.name());
		CreateAgentResponse response = agentService.createAgent(request);
		log.debug("Created agent with id: {}", response.id());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}