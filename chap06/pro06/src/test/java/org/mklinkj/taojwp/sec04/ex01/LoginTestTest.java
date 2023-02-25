package org.mklinkj.taojwp.sec04.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginTestTest extends MockHttpServletTestSupport<LoginTest> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/loginTest");
    setServlet(new LoginTest());
  }

  @Test
  void testLoginSuccess() throws IOException {
    request.setParameter("user_id", "park");
    request.setParameter("user_pw", "1234");

    servlet.doPost(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .isEqualTo(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 성공</title>
            </head>
            <body>
              <h4>park님 로그인하셨습니다.</h4>
            </body>
            </html>
            """);
  }

  @Test
  void testLoginFail() throws IOException {
    request.setParameter("user_id", "");
    request.setParameter("user_pw", "1234");

    servlet.doPost(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .isEqualTo(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 실패</title>
            </head>
            <body>
              <h4>아이디를 입력하세요.</h4>
              <a href="/pro06/test01/login.html">로그인 창으로 이동</a>
            </body>
            </html>
            """);
  }
}
