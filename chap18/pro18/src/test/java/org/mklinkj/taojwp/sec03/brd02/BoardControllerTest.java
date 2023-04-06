package org.mklinkj.taojwp.sec03.brd02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockPart;

class BoardControllerTest extends MockHttpServletTestSupport<BoardController> {
  @BeforeEach
  public void beforeEach() {}

  @Test
  void testDoHandle_list() throws Exception {
    runGivenWhenThen(
        () -> servlet.init(), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(request.getAttribute("articleList")).isNotNull();
          assertThat(response.getForwardedUrl()).isEqualTo("/board02/listArticles.jsp");
        });
  }

  @Test
  void testDoHandle_articleForm() throws Exception {
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setPathInfo("/articleForm.do");
        },
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()).isEqualTo("/board02/articleForm.jsp");
        });
  }

  @Test
  void testDoHandle_addArticle() throws Exception {
    dbDataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setPathInfo("/addArticle.do");

          request.setParameter("title", "제목");
          request.setParameter("content", "내용");
          request.addPart(new MockPart("title", null));
          request.addPart(new MockPart("content", null));
          // 아래와 같은 식으로 테스트가 불가능하다.
          // MockPart의
          /*
          request.addPart(
              new MockPart(
                  "imageFileName", "이미지파일.txt", "이미지파일_흉내".getBytes(StandardCharsets.UTF_8)));

          */
        },
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()).isEqualTo(getServletPath() + "/listArticles.do");
        });
  }

  @Override
  protected Class<BoardController> getServletClass() {
    return BoardController.class;
  }

  @Override
  protected String getServletPath() {
    return "/board2";
  }
}
