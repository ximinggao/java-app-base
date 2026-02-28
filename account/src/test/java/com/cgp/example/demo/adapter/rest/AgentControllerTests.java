package com.cgp.example.demo.adapter.rest;

import com.cgp.example.demo.application.port.out.AgentPersistencePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AgentControllerTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private AgentPersistencePort agentPersistencePort;

	@Test
	void shouldCreateAgentSuccessfully() throws Exception {
		// Given
		String agentName = "TestAgent";
		Long expectedId = 1L;
		when(agentPersistencePort.existsByName(agentName)).thenReturn(false);
		when(agentPersistencePort.save(any())).thenReturn(new com.cgp.example.demo.domain.model.Agent(expectedId, agentName));

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		// When & Then
		mockMvc.perform(post("/agents")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"" + agentName + "\"}"))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(expectedId));
	}

	@Test
	void shouldReturnBadRequestWhenNameIsBlank() throws Exception {
		// Given
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		// When & Then
		mockMvc.perform(post("/agents")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"\"}"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnConflictWhenNameAlreadyExists() throws Exception {
		// Given
		String agentName = "ExistingAgent";
		when(agentPersistencePort.existsByName(agentName)).thenReturn(true);

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		// When & Then
		mockMvc.perform(post("/agents")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"" + agentName + "\"}"))
			.andExpect(status().isConflict());
	}

	@TestConfiguration
	static class TestConfig {

		@Bean
		@Primary
		AgentPersistencePort agentPersistencePort() {
			return mock(AgentPersistencePort.class);
		}
	}
}