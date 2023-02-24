package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginServletTest extends MockHttpServletTestSupport {

  private LoginServlet servlet;

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/login");
    servlet = new LoginServlet();
  }

  @Test
  void testDoGet() throws IOException {
    request.setParameter("user_id", "choi");
    request.setParameter("user_pw", "1212");

    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }
}
