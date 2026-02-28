package com.cgp.example.demo.application.port.in;

/**
 * Inbound port for agent operations.
 */
public interface AgentService {

	/**
	 * Creates a new agent with a unique name.
	 *
	 * @param request the creation request containing the agent name
	 * @return the response containing the generated agent ID
	 * @throws IllegalArgumentException if the name is null or blank
	 * @throws IllegalStateException if an agent with the same name already exists
	 */
	CreateAgentResponse createAgent(CreateAgentRequest request);
}