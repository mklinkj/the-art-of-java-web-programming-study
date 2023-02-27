package org.mklinkj.taojwp.sec01.ex03;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class FirstServletTest extends MockHttpServletTestSupport<FirstServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/first2");
    setServlet(new FirstServlet());
  }

  @Test
  void testDoGet() throws IOException {
    servlet.doGet(request, response);
    assertThat(response.getStatus())
        .isEqualTo(HttpStatus.OK.value())
        .describedAs("location JavaScript 코드설정을 통한 응답은 200으로 받음");
    assertThat(response.getContentAsString()) //
        .contains("location.href='second?forwardingType=location';");
  }
}
