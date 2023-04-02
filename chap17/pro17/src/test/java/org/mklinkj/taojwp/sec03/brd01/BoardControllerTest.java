package org.mklinkj.taojwp.sec03.brd01;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class BoardControllerTest extends MockHttpServletTestSupport<BoardController> {

  @Test
  void testDoHandle_list() throws Exception {
    runGivenWhenThen(
        () -> servlet.init(), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(request.getAttribute("articleList")).isNotNull();
          assertThat(response.getForwardedUrl()).isEqualTo("/board01/listArticles.jsp");
        });
  }

  @Override
  protected Class<BoardController> getServletClass() {
    return BoardController.class;
  }

  @Override
  protected String getServletPath() {
    return "/board";
  }
}
