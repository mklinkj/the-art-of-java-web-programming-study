package org.mklinkj.taojwp.sec01.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class SecondServletTest extends MockHttpServletTestSupport<SecondServlet> {

  @Test
  void testLogin() throws IOException {
    request.setParameter("user_id", "lee");
    request.setParameter("user_pw", "1234");
    // hidden 폼도 동일하게 request 파라미터를 통해 유입됨.
    request.setParameter("user_address", "서울시 성북구");

    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    assertThat(response.getContentAsString())
        .contains("첫번째 서블릿에서 넘겨준 아이디: lee")
        .contains("첫번째 서블릿에서 넘겨준 주소: 서울시 성북구");
  }

  @Test
  void testNoLogin() throws IOException {
    request.setParameter("user_id", "");

    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    assertThat(response.getContentAsString())
        .contains("로그인 하지 않았습니다.")
        .contains("/pro09/login.html");
  }

  @Override
  protected Class<SecondServlet> getServletClass() {
    return SecondServlet.class;
  }

  @Override
  protected String getServletPath() {
    return "/second";
  }
}
