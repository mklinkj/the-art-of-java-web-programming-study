package org.mklinkj.taojwp.sec01.ex03;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class DispatchForwardSecondServletTest
    extends MockHttpServletTestSupport<DispatchForwardSecondServlet> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/dispatchForwardSecond");
    setServlet(new DispatchForwardSecondServlet());
  }

  @Test
  void testDoGet() throws IOException {
    request.setParameter("name", "자바");
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains("dispatch를 이용한 forward 실습입니다.");
    assertThat(response.getContentAsString()).contains("이름: 자바");
  }
}
