package com.cgp.account.application.port.out;

import com.cgp.account.domain.model.Agent;

/**
 * Outbound port for agent persistence operations.
 */
public interface AgentPersistencePort {

	Agent save(Agent agent);

	boolean existsByName(String name);
}