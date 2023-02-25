package org.mklinkj.taojwp.sec03.ex03;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginServlet5Test extends MockHttpServletTestSupport<LoginServlet5> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/login5");
    setServlet(new LoginServlet5());
  }

  @Test
  void testDoPost() throws IOException {
    request.setParameter("user_id", "park");
    request.setParameter("user_pw", "1234");
    request.setParameter("user_address", "서울시 성북구");

    servlet.doPost(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .isEqualTo(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 정보</title>
            </head>
            <body>
              <h4>아이디: park</h4>
              <h4>패스워드: 1234</h4>
              <h4>주소: 서울시 성북구</h4>
            </body>
            </html>
            """);
  }
}
