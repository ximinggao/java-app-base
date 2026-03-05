package com.cgp.account.application.service;

import com.cgp.account.application.port.in.AgentService;
import com.cgp.account.application.port.in.CreateAgentRequest;
import com.cgp.account.application.port.in.CreateAgentResponse;
import com.cgp.account.application.port.out.AgentPersistencePort;
import com.cgp.account.domain.model.Agent;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.ObservationKeyValue;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the AgentService port.
 * Contains the business logic for agent operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentPersistencePort agentPersistencePort;
    private final ObservationRegistry observationRegistry;

    @Override
    @Transactional
    @Observed(name = "create-agent")
    public CreateAgentResponse createAgent(@ObservationKeyValue(key = "agent.name") CreateAgentRequest request) {
        log.debug("Creating agent with name: {}", request.name());

        validateRequest(request);
        ensureNameIsUnique(request.name());

        Agent agent = new Agent(null, request.name());

        Agent savedAgent = Observation.createNotStarted("save-agent", this.observationRegistry)
                .observe(() -> agentPersistencePort.save(agent));

        log.info("Created agent with id: {} and name: {}", savedAgent.id(), savedAgent.name());
        return new CreateAgentResponse(savedAgent.id());
    }

    private void validateRequest(CreateAgentRequest request) {
        if (request.name() == null || request.name().isBlank()) {
            throw new IllegalArgumentException("Agent name cannot be null or blank");
        }
    }

    private void ensureNameIsUnique(String name) {
        if (agentPersistencePort.existsByName(name)) {
            throw new IllegalStateException("Agent with name '" + name + "' already exists");
        }
    }
}