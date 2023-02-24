package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class SecondServletServletTest extends MockHttpServletTestSupport {

  private SecondServlet servlet;

  @BeforeEach
  public void beforeEach() {
    request.setContextPath("/pro05");
    request.setServletPath("/second");
    servlet = new SecondServlet();
  }

  /** 진짜 URL을 호출해서 하는 방식은 아니여서, 메서드를 각각 호출해주었다. */
  @Test
  void testServlet() throws IOException {
    servlet.init();
    servlet.doGet(request, response);
    servlet.destroy();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("SecondServlet");
  }
}
