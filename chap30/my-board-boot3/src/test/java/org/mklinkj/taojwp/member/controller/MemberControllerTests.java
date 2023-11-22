package org.mklinkj.taojwp.member.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
@WithUserDetails("mklinkj")
class MemberControllerTests {

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @BeforeEach
  public void beforeEach() {
    mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
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
        .andExpect(view().name("member/listMembers"));
  }

  @Test
  void testListMembersDo_with_Params() throws Exception {
    mockMvc
        .perform(
            get("/member/listMembers.do") //
                .param("type", "ID")
                .param("keyword", ""))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("memberList"))
        .andExpect(view().name("member/listMembers"));
  }

  @Test
  void testMemberFormDo() throws Exception {
    mockMvc
        .perform(get("/member/memberForm.do"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("member/memberForm"));
  }

  @Test
  void testMemberDetailDo() throws Exception {
    mockMvc
        .perform(get("/member/memberDetail.do").param("id", "mklinkj"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("member/memberDetail"))
        .andExpect(model().attributeExists("member"));
  }

  @Transactional
  @Rollback
  @Test
  void testAddMemberDo_01() throws Exception {
    mockMvc
        .perform(
            post("/member/addMember.do")
                .param("id", "mklinkj2")
                .param("pwd", "4321")
                .param("name", "정션링크2")
                .param("email", "mklinkj2@github.com")
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("/member/listMembers.do"))
        .andExpect(flash().attribute("result", "register.success"));
  }

  @Test
  void testAddMemberDo_02() throws Exception {
    mockMvc
        .perform(
            post("/member/addMember.do")
                .param("id", "1")
                .param("pwd", "1")
                .param("name", "정션링크2")
                .param("email", "mklinkj2@github.com")
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(view().name("member/memberForm"));
  }

  @Test
  void testAddMemberDo_03() throws Exception {
    mockMvc
        .perform(get("/member/addMember.do"))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("/member/memberForm.do"));
  }

  @Transactional
  @Rollback
  @Test
  void testModMemberDo_01() throws Exception {
    mockMvc
        .perform(
            post("/member/modMember.do")
                .param("id", "mklinkj")
                .param("pwd", "4321")
                .param("name", "정션링크_수정")
                .param("email", "mklinkj_mod@github.com")
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("/member/modMemberForm.do?id=mklinkj"))
        .andExpect(flash().attribute("result", "modify.success"));
  }

  @Test
  void testModMemberDo_02() throws Exception {
    mockMvc
        .perform(
            post("/member/modMember.do")
                .param("id", "mklinkj")
                .param("pwd", "1")
                .param("name", "정션링크2")
                .param("email", "mklinkj2@github.com")
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(view().name("member/modMemberForm"));
  }

  @Test
  void testModMemberDo_03() throws Exception {
    mockMvc
        .perform(get("/member/modMember.do"))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("/member/modMemberForm.do"));
  }

  @Transactional
  @Rollback
  @Test
  void testDelMember() throws Exception {
    mockMvc
        .perform(
            post("/member/delMember.do") //
                .param("id", "no_article")
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl("/member/listMembers.do"));
  }
}
