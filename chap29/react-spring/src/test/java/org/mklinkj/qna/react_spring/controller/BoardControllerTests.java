package org.mklinkj.qna.react_spring.controller;

import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.qna.react_spring.config.ServiceConfig;
import org.mklinkj.qna.react_spring.config.WebConfiguration;
import org.mklinkj.qna.react_spring.dto.ArticleDTO;
import org.mklinkj.qna.react_spring.test.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(classes = {ServiceConfig.class, WebConfiguration.class})
class BoardControllerTests {
  private MockMvc mockMvc;

  @Autowired private WebApplicationContext context;

  @Autowired private TestHelper testHelper;

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void beforeEach() {
    mockMvc = webAppContextSetup(context).build();
  }

  @Test
  void testAll() throws Exception {
    mockMvc
        .perform(get("/board/all")) //
        .andExpect(status().isOk())
        .andExpect(json().isPresent())
        .andExpect(json().isArray())
        .andExpect(json().node("[1].writer").isEqualTo("이순신1"));
  }

  @Test
  void testGetArticle() throws Exception {
    mockMvc
        .perform(get("/board/2")) //
        .andExpect(status().isOk())
        .andExpect(json().isPresent())
        .andExpect(json().node("writer").isEqualTo("이순신2"));

    mockMvc
        .perform(get("/board/5")) //
        .andExpect(status().isOk())
        .andExpect(json().isPresent())
        .andExpect(json().node("title").isEqualTo("안녕하세요5"));
  }

  @Transactional
  @Test
  void testAddArticle() throws Exception {
    Integer articleNo = testHelper.createNewId();
    mockMvc
        .perform(
            post("/board/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(
                        ArticleDTO.builder() //
                            .articleNo(articleNo)
                            .title("안녕하세요%d".formatted(articleNo))
                            .content("새 상품을 소개합니다. %d".formatted(articleNo))
                            .writer("이순신%d".formatted(articleNo))
                            .build()))) //
        .andExpect(status().isOk())
        .andExpect(json().isPresent())
        .andExpect(json().node("result").isEqualTo("success"));
  }

  @Transactional
  @Test
  void testModifyArticle() throws Exception {
    Integer articleNo = 2;

    mockMvc
        .perform(
            put("/board/%d".formatted(articleNo))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(
                        ArticleDTO.builder() //
                            .articleNo(2)
                            .title("안녕하세요%d".formatted(200))
                            .content("새 상품을 소개합니다. %d".formatted(200))
                            .writer("이순신%d".formatted(200))
                            .build()))) //
        .andExpect(status().isOk())
        .andExpect(json().isPresent())
        .andExpect(json().node("result").isEqualTo("success"));
  }

  @Transactional
  @Test
  void testDeleteArticle() throws Exception {
    mockMvc
        .perform(delete("/board/3")) //
        .andExpect(status().isOk())
        .andExpect(json().isPresent())
        .andExpect(json().node("result").isEqualTo("success"));
  }
}
