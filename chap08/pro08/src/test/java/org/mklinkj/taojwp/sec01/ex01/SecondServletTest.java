package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class SecondServletTest extends MockHttpServletTestSupport<SecondServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/second");
    setServlet(new SecondServlet());
  }

  @Test
  void testDoGet() throws IOException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .contains("<h4>sendRedirect를 이용한 redirect 실습입니다.</h4>");
  }
}
