package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class InputServlet2Test extends MockHttpServletTestSupport<InputServlet2> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/input2");
    setServlet(new InputServlet2());
  }

  @Test
  void testDoGet() throws IOException {
    request.setParameter("user_id", "choi");
    request.setParameter("user_pw", "1234");

    request.setParameter("subject", "java", "C언어", "JSP", "안드로이드");

    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }
}
