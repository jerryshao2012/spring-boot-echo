package com.ibm.client.engineering.echo;

import com.ibm.client.engineering.echo.controller.EchoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = EchoController.class)
class EchoApplicationTests {

	// ----------------------------------------------------- Instance Variables

	@Autowired
	private MockMvc mockMvc;

	// ----------------------------------------------------- Private Methods

	// ----------------------------------------------------- Protected Methods

	// ----------------------------------------------------- Public Methods
	@Test
	public void homePage() throws Exception {
		// N.B. jsoup can be useful for asserting HTML content
		mockMvc.perform(get("/index.html"))
				.andExpect(content().string(containsString("Echo Website with Spring MVC")));
	}

	@Test
	public void echo() throws Exception {
		mockMvc.perform(get("/echo"))
				.andExpect(content().string(containsString("Echo HTTP Request")));
	}

	@Test
	public void echoWithParameters() throws Exception {
		mockMvc.perform(get("/echo").param("name", "jerry"))
				.andExpect(content().string(containsString("name=jerry")));
	}

}
