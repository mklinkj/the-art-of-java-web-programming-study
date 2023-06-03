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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ContextConfiguration(
    locations = {
      "classpath:action-servlet.xml",
      "classpath:config/action-service.xml",
      "classpath:config/action-dataSource.xml"
    })
@WebAppConfiguration
@Slf4j
public class MemberControllerTests {

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void before() {
    mockMvc = webAppContextSetup(context).build();
  }

  @Test
  public void testWebApplicationContext() {
    LOGGER.info("### 웹 컨텍스트 로드 ###");
    assertThat(context).isNotNull();
  }

  @Test
  public void testListMembersDo() throws Exception {
    mockMvc
        .perform(get("/member/listMembers.do"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("memberList"))
        .andExpect(view().name("listMembers"))
        .andExpect(forwardedUrl("/WEB-INF/views/listMembers.jsp"));
  }

  @Test
  public void testMemberFormDo() throws Exception {
    mockMvc
        .perform(get("/member/memberForm.do"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("memberForm"))
        .andExpect(forwardedUrl("/WEB-INF/views/memberForm.jsp"));
  }

  @Test
  public void testMemberDetailDo() throws Exception {
    mockMvc
        .perform(get("/member/memberDetail.do").param("id", "mklinkj"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("memberDetail"))
        .andExpect(model().attributeExists("member"))
        .andExpect(forwardedUrl("/WEB-INF/views/memberDetail.jsp"));
  }

  @Test
  public void testAddMemberDo() throws Exception {
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
