package org.mklinkj.taojwp.sec01.ex04;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class RedirectBindingFirstServletTest
    extends MockHttpServletTestSupport<RedirectBindingFirstServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/redirectBindingFirst");
    setServlet(new RedirectBindingFirstServlet());
  }

  @Test
  void testDoGet() throws IOException, ServletException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
    assertThat(response.getRedirectedUrl()).isEqualTo("redirectBindingSecond");
    assertThat(request.getAttribute("address")).isEqualTo("서울시 성북구");
  }
}
