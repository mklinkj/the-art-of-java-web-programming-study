package org.mklinkj.taojwp.sec04.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginTestTest extends MockHttpServletTestSupport<LoginTest> {

  @Test
  void testDoPost() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("user_id", "아이디01");
          request.setParameter("user_pw", "1234");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(response.getContentAsString()) //
              .contains("아이디는 아이디01")
              .doesNotContain("총 접속자수 0")
              .containsPattern("총 접속자수 \\d+");
        });
  }

  @Override
  protected Class<LoginTest> getServletClass() {
    return LoginTest.class;
  }

  @Override
  protected String getServletPath() {
    return "/userCountLogin";
  }
}
