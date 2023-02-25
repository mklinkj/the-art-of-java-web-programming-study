package org.mklinkj.taojwp.sec04.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginTest2Test extends MockHttpServletTestSupport<LoginTest2> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/login");
    setServlet(new LoginTest2());
  }

  @Test
  void testAdminLogin() throws IOException {
    request.setParameter("user_id", "admin");
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
              <title>로그인 성공 - 관리자</title>
            </head>
            <body>
              <h4>관리자로 로그인하셨습니다.</h4>
              <input type="button" value='회원정보 수정하기' />
              <input type="button" value='회원정보 삭제하기' />
            </body>
            </html>
            """);
  }

  @Test
  void testUserLogin() throws IOException {
    request.setParameter("user_id", "choi");
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
              <h4>choi님 로그인하셨습니다.</h4>
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
