package com.cgp.example.demo.application.port.out;

import com.cgp.example.demo.domain.model.Agent;

/**
 * Outbound port for agent persistence operations.
 */
public interface AgentPersistencePort {

	Agent save(Agent agent);

	boolean existsByName(String name);
}