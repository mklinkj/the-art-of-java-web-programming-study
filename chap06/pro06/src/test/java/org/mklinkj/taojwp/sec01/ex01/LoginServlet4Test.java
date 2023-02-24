package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginServlet4Test extends MockHttpServletTestSupport<LoginServlet4> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/login4");
    setServlet(new LoginServlet4());
  }

  @Test
  void testDoGet() throws IOException {
    request.setParameter("user_id", "park");
    request.setParameter("user_pw", "1234");

    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  void testDoPost() throws IOException {
    request.setParameter("user_id", "choi");
    request.setParameter("user_pw", "1212");

    servlet.doPost(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }
}
