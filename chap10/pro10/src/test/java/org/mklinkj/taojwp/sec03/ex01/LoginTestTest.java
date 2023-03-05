package org.mklinkj.taojwp.sec03.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginTestTest extends MockHttpServletTestSupport<LoginTest> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("user_name", "한글이름");
          request.setParameter("user_pw", "1234");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(response.getContentAsString()) //
              .contains("이름은 한글이름")
              .contains("비밀번호는 1234");
        });
  }

  @Override
  protected Class<LoginTest> getServletClass() {
    return LoginTest.class;
  }

  @Override
  protected String getServletPath() {
    return "*.do";
  }
}
