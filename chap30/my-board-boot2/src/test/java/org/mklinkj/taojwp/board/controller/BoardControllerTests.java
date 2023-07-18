package org.mklinkj.taojwp.board.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
@WithUserDetails("mklinkj")
class BoardControllerTests {
  @Autowired private WebApplicationContext context;

  @Autowired private DBDataInitializer dbDataInitializer;

  private final String IMAGE_FILE_01_NAME = "image_file_01.png";
  private final String IMAGE_FILE_02_NAME = "image_file_02.png";

  private final String UPLOAD_PATH_FORMAT = "/upload_test_file/%s";

  private MockMvc mockMvc;

  @BeforeEach
  void beforeEach() {
    mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
  }

  @Test
  void testListArticles() throws Exception {
    dbDataInitializer.resetDB();
    mockMvc
        .perform(
            get("/board/listArticles.do") //
                .param("section", "2")
                .param("pageNum", "1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("articlesMap"))
        .andExpect(view().name("board/listArticles"));
  }

  @Test
  void testViewArticle() throws Exception {
    dbDataInitializer.resetDB();
    mockMvc
        .perform(
            get("/board/viewArticle.do") //
                .param("articleNo", "20"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("article"))
        .andExpect(model().attributeExists("attachFileList"))
        .andExpect(view().name("board/viewArticle"));
  }

  @Test
  void testArticleForm() throws Exception {
    mockMvc
        .perform(get("/board/articleForm.do"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("board/articleForm"));
  }

  @Test
  void testAddArticle() throws Exception {
    dbDataInitializer.resetDB();
    EncodedResource imageFile01 =
        new EncodedResource(
            new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_01_NAME)),
            StandardCharsets.UTF_8);

    EncodedResource imageFile02 =
        new EncodedResource(
            new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_02_NAME)),
            StandardCharsets.UTF_8);

    MockMultipartFile multipartFile01 =
        new MockMultipartFile(
            "imageFile",
            IMAGE_FILE_01_NAME,
            MediaType.IMAGE_PNG_VALUE,
            imageFile01.getInputStream());

    MockMultipartFile multipartFile02 =
        new MockMultipartFile(
            "imageFile",
            IMAGE_FILE_02_NAME,
            MediaType.IMAGE_PNG_VALUE,
            imageFile02.getInputStream());

    mockMvc
        .perform(
            multipart(HttpMethod.POST, "/board/addArticle.do")
                .file(multipartFile01)
                .file(multipartFile02)
                .param("title", "제목")
                .param("content", "내용")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(
            flash()
                .attribute(
                    "msg",
                    ModalMessage.builder()
                        .title("🎊 등록 성공 🎊")
                        .content("새 게시글 등록에 성공하였습니다.🎉")
                        .build()))
        .andExpect(redirectedUrl("listArticles.do"));
  }

  @Test
  void testModArticle() throws Exception {
    dbDataInitializer.resetDB();
    EncodedResource imageFile01 =
        new EncodedResource(
            new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_01_NAME)),
            StandardCharsets.UTF_8);

    EncodedResource imageFile02 =
        new EncodedResource(
            new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_02_NAME)),
            StandardCharsets.UTF_8);

    MockMultipartFile multipartFile01 =
        new MockMultipartFile(
            "imageFile",
            IMAGE_FILE_01_NAME,
            MediaType.IMAGE_PNG_VALUE,
            imageFile01.getInputStream());

    MockMultipartFile multipartFile02 =
        new MockMultipartFile(
            "imageFile",
            IMAGE_FILE_02_NAME,
            MediaType.IMAGE_PNG_VALUE,
            imageFile02.getInputStream());

    mockMvc
        .perform(
            multipart(HttpMethod.POST, "/board/modArticle.do")
                .file(multipartFile01)
                .file(multipartFile02)
                // 수정시 삭제할 기존 이미지의 UUID 목록
                .param("uuidsToDelete", "dab145b3-9cec-4e58-a9d2-8be341d16093")
                .param("uuidsToDelete", "4f4fa02f-570a-46b9-9ed8-5218b568d97e")
                .param("articleNo", "30")
                .param("title", "제목_수정")
                .param("content", "내용_수정")
                .param("originalFileName", "") // 먼저 등록된 첨부파일 이름
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(
            flash()
                .attribute(
                    "msg",
                    ModalMessage.builder()
                        .title("🎊 수정 성공 🎊")
                        .content("게시글 수정에 성공하였습니다.🎉")
                        .build()))
        .andExpect(redirectedUrl("viewArticle.do?articleNo=30"));
  }

  @Test
  @WithMockUser("mklinkj")
  void testRemoveArticle() throws Exception {
    dbDataInitializer.resetDB();

    mockMvc
        .perform(
            post("/board/removeArticle.do") //
                .param("articleNo", "30")
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(
            flash()
                .attribute(
                    "msg",
                    ModalMessage.builder()
                        .title("🎊 삭제 성공 🎊")
                        .content("게시글 삭제에 성공하였습니다.🎉")
                        .build()))
        .andExpect(redirectedUrl("listArticles.do"));
  }

  @Test
  void testReplyForm() throws Exception {
    mockMvc
        .perform(
            post("/board/replyForm.do") //
                .param("parentNo", "1")
                .with(csrf()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("board/replyForm"))
        .andExpect(
            result ->
                assertThat(
                        Objects.requireNonNull(result.getRequest().getSession())
                            .getAttribute("parentNo")) //
                    .isEqualTo(1));
  }

  @Test
  void testAddReply() throws Exception {
    dbDataInitializer.resetDB();
    EncodedResource imageFile01 =
        new EncodedResource(
            new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_01_NAME)),
            StandardCharsets.UTF_8);

    EncodedResource imageFile02 =
        new EncodedResource(
            new ClassPathResource(UPLOAD_PATH_FORMAT.formatted(IMAGE_FILE_02_NAME)),
            StandardCharsets.UTF_8);

    MockMultipartFile multipartFile01 =
        new MockMultipartFile(
            "imageFile",
            IMAGE_FILE_01_NAME,
            MediaType.IMAGE_PNG_VALUE,
            imageFile01.getInputStream());

    MockMultipartFile multipartFile02 =
        new MockMultipartFile(
            "imageFile",
            IMAGE_FILE_02_NAME,
            MediaType.IMAGE_PNG_VALUE,
            imageFile02.getInputStream());
    mockMvc
        .perform(
            multipart(HttpMethod.POST, "/board/addReply.do")
                .file(multipartFile01)
                .file(multipartFile02)
                .param("title", "답글 제목")
                .param("content", "답글 내용")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .with(csrf())
                .with(
                    request -> {
                      Objects.requireNonNull(request.getSession()).setAttribute("parentNo", 1);
                      return request;
                    }))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(
            flash()
                .attribute(
                    "msg",
                    ModalMessage.builder()
                        .title("🎊 답글 추가 성공 🎊")
                        .content("답글 게시글을 추가하였습니다.🎉")
                        .build()))
        .andExpect(
            result ->
                assertThat(result.getResponse().getRedirectedUrl())
                    .containsPattern("viewArticle.do\\?articleNo=\\d+"));
  }
}
