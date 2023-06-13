package org.mklinkj.taojwp.common.controller;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.test.web.servlet.MvcResult;
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

    MvcResult result =
        mockMvc
            .perform(get("/webjars_locator/bootstrap/js/bootstrap.bundle.min.js"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

    // Files.probeContentType() 의 반환 값이 시스템이 따라 달라질 때가 있는 것 같다.
    assertThat(result.getResponse().getContentType()) //
        .containsAnyOf("application/javascript", "text/javascript");
  }

  @Test
  void testWebjarsLocator_jQuery() throws Exception {
    MvcResult result =
        mockMvc
            .perform(get("/webjars_locator/jquery/jquery.slim.min.js"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

    // Files.probeContentType() 의 반환 값이 시스템이 따라 달라질 때가 있는 것 같다.
    assertThat(result.getResponse().getContentType()) //
        .containsAnyOf("application/javascript", "text/javascript");
  }
}
