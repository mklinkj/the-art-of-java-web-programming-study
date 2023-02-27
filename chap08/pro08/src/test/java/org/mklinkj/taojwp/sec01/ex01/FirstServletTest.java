package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class FirstServletTest extends MockHttpServletTestSupport<FirstServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/first");
    setServlet(new FirstServlet());
  }

  @Test
  void testDoGet() throws IOException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
    assertThat(response.getRedirectedUrl()).isEqualTo("second?forwardingType=sendRedirect&name=lee");
  }
}
