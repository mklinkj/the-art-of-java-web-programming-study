package org.mklinkj.taojwp.member.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
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
      "classpath:config/action-service.xml",
      "classpath:config/action-dataSource.xml"
    })
@Slf4j
class MemberControllerTests {

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @BeforeEach
  public void beforeEach() {
    mockMvc = webAppContextSetup(context).build();
  }

  @Test
  void testWebApplicationContext() {
    LOGGER.info("### 웹 컨텍스트 로드 ###");
    assertThat(context).isNotNull();
  }

  @Test
  void testListMembersDo() throws Exception {
    mockMvc
        .perform(get("/member/listMembers.do"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("memberList"))
        .andExpect(view().name("member/listMembers"))
        .andExpect(forwardedUrl("/WEB-INF/views/member/listMembers.jsp"));
  }

  @Test
  void testMemberFormDo() throws Exception {
    mockMvc
        .perform(get("/member/memberForm.do"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("member/memberForm"))
        .andExpect(forwardedUrl("/WEB-INF/views/member/memberForm.jsp"));
  }

  @Test
  void testMemberDetailDo() throws Exception {
    mockMvc
        .perform(get("/member/memberDetail.do").param("id", "mklinkj"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("member/memberDetail"))
        .andExpect(model().attributeExists("member"))
        .andExpect(forwardedUrl("/WEB-INF/views/member/memberDetail.jsp"));
  }

  @Test
  void testAddMemberDo() throws Exception {
    mockMvc
        .perform(
            post("/member/addMember.do")
                .param("id", "mklinkj2")
                .param("pwd", "4321")
                .param("name", "정션링크2")
                .param("email", "mklinkj2@github.com"))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("/member/listMembers.do"));
  }
}
