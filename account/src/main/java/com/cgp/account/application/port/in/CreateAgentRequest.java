package com.cgp.account.application.port.in;

/**
 * Request model for creating a new agent.
 *
 * @param name the name of the agent (must be unique)
 */
public record CreateAgentRequest(String name) {
}