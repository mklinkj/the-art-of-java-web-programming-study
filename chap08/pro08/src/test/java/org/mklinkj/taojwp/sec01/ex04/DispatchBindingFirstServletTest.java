package org.mklinkj.taojwp.sec01.ex04;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class DispatchBindingFirstServletTest
    extends MockHttpServletTestSupport<DispatchBindingFirstServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/dispatchBindingFirst");
    setServlet(new DispatchBindingFirstServlet());
  }

  @Test
  void testDoGet() throws IOException, ServletException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getForwardedUrl()).isEqualTo("dispatchBindingSecond");
    assertThat(request.getAttribute("address")).isEqualTo("서울시 성북구");
  }
}
