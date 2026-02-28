package com.cgp.account.adapter.rest;

import com.cgp.account.domain.service.GreetingWorld;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GreetingControllerTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private GreetingWorld greetingWorld;

	@Test
	void shouldReturnGreetingMessage() throws Exception {
		// Given
		when(greetingWorld.getGreeting()).thenReturn("World");

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		// When & Then
		mockMvc.perform(get("/greeting"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello, World"));
	}

	@Test
	void shouldReturnCustomGreetingMessage() throws Exception {
		// Given
		when(greetingWorld.getGreeting()).thenReturn("Universe");

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		// When & Then
		mockMvc.perform(get("/greeting"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello, Universe"));
	}

	@TestConfiguration
	static class TestConfig {

		@Bean
		@Primary
		GreetingWorld greetingWorld() {
			return mock(GreetingWorld.class);
		}
	}
}
