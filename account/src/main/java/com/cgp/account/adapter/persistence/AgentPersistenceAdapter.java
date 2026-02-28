package com.cgp.account.adapter.persistence;

import com.cgp.account.application.port.out.AgentPersistencePort;
import com.cgp.account.domain.model.Agent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Persistence adapter implementing the AgentPersistencePort.
 * Handles conversion between domain model and JPA entity.
 */
@Component
@RequiredArgsConstructor
public class AgentPersistenceAdapter implements AgentPersistencePort {

	private final AgentRepository agentRepository;

	@Override
	public Agent save(Agent agent) {
		AgentJpaEntity entity = AgentJpaEntity.builder()
			.name(agent.name())
			.build();
		AgentJpaEntity savedEntity = agentRepository.save(entity);
		return new Agent(savedEntity.getId(), savedEntity.getName());
	}

	@Override
	public boolean existsByName(String name) {
		return agentRepository.existsByName(name);
	}
}