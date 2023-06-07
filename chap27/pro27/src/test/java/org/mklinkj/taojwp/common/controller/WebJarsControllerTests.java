package org.mklinkj.taojwp.common.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(
    locations = {
      "classpath:action-servlet.xml",
      "classpath:config/action-repository.xml",
      "classpath:config/action-service.xml",
      "classpath:config/action-security.xml"
    })
@Slf4j
class WebJarsControllerTests {

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @BeforeEach
  void beforeEach() {
    mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
  }

  @Test
  void testWebjarsLocator_Bootstrap() throws Exception {
    mockMvc
        .perform(get("/webjars_locator/bootstrap/css/bootstrap.min.css"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/css"));

    mockMvc
        .perform(get("/webjars_locator/bootstrap/js/bootstrap.bundle.min.js"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/javascript"));
  }

  @Test
  void testWebjarsLocator_jQuery() throws Exception {
    mockMvc
        .perform(get("/webjars_locator/jquery/jquery.slim.min.js"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/javascript"));
  }
}
