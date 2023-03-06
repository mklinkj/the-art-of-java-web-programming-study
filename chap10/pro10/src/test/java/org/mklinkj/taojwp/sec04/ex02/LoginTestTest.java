package org.mklinkj.taojwp.sec04.ex02;

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
              .contains("접속 아이디")
              .contains("아이디01")
              // HttpSessionBindingListener와는 다르게 HttpSessionListener는 Mock만으로 처리가 안된다. 컨테이너가 처리해줘야하나봄.
              // 그래서 총 접속자수는 0으로 나타난다.
              .contains("총 접속자수 0")
              .containsPattern("총 접속자수 \\d+");
        });
  }

  @Override
  protected Class<LoginTest> getServletClass() {
    return LoginTest.class;
  }

  @Override
  protected String getServletPath() {
    return "/userCountLogin2";
  }
}
