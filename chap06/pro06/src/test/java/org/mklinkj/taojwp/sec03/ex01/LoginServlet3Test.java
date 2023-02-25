package org.mklinkj.taojwp.sec03.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginServlet3Test extends MockHttpServletTestSupport<LoginServlet3> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/login3");
    setServlet(new LoginServlet3());
  }

  @Test
  void testDoPost() throws IOException {
    request.setParameter("user_id", "park");
    request.setParameter("user_pw", "1234");

    servlet.doPost(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }
}
