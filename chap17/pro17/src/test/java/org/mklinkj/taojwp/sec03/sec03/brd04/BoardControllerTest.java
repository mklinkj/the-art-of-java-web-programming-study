package org.mklinkj.taojwp.sec03.sec03.brd04;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockPart;

class BoardControllerTest extends MockHttpServletTestSupport<BoardController> {

  @Test
  void testDoHandle_list() throws Exception {
    runGivenWhenThen(
        () -> servlet.init(), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(request.getAttribute("articleList")).isNotNull();
          assertThat(response.getForwardedUrl()).isEqualTo("/board03/listArticles.jsp");
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
          assertThat(response.getForwardedUrl()).isEqualTo("/board03/articleForm.jsp");
        });
  }

  @Test
  void testDoHandle_addArticle() throws Exception {
    resetDB();
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

  @Test
  void testDoHandle_modArticle() throws Exception {
    resetDB();
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setPathInfo("/modArticle.do");

          request.setParameter("articleNo", "1");
          request.setParameter("title", "제목_수정");
          request.setParameter("content", "내용_수정");
          request.addPart(new MockPart("articleNo", null));
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
          assertThat(request.getSession().getAttribute("msg"))
              .isEqualTo(
                  ModalMessage.builder()
                      .title("🎊 수정 성공 🎊")
                      .content("게시글 수정에 성공하였습니다.🎉")
                      .build());
          assertThat(response.getRedirectedUrl())
              .isEqualTo(getServletPath() + "/viewArticle.do?articleNo=1");
        });
  }

  @Override
  protected Class<BoardController> getServletClass() {
    return BoardController.class;
  }

  @Override
  protected String getServletPath() {
    return "/board4";
  }
}
