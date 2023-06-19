package org.mklinkj.qna.react_spring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.qna.react_spring.config.ServiceConfig;
import org.mklinkj.qna.react_spring.config.WebConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringJUnitWebConfig(classes = {ServiceConfig.class, WebConfiguration.class})
class ApiControllerTests {
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new ApiController()).build();
  }

  @Test
  void testWelcome() throws Exception {
    mockMvc
        .perform(get("/api/welcome")) //
        .andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("welcome")));
  }
}
