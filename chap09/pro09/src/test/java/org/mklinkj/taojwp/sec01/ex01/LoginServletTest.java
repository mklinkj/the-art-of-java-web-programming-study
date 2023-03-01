package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginServletTest extends MockHttpServletTestSupport<LoginServlet> {

  @Test
  void testLogin() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("user_id", "lee");
          request.setParameter("user_pw", "1234");
          // hidden 폼도 동일하게 request 파라미터를 통해 유입됨.
          request.setParameter("user_address", "서울시 성북구");
          request.setParameter("user_email", "test@gmail.com");
          request.setParameter("user_hp", "010-111-2222");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(response.getContentAsString())
              .contains("아이디: lee") //
              .contains("주소: 서울시 성북구")
              .contains("휴대 전화: 010-111-2222");
        });
  }

  @Override
  protected Class<LoginServlet> getServletClass() {
    return LoginServlet.class;
  }

  @Override
  protected String getServletPath() {
    return "/login";
  }
}
