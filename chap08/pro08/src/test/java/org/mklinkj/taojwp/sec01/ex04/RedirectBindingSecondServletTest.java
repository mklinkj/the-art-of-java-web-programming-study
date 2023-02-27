package org.mklinkj.taojwp.sec01.ex04;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class RedirectBindingSecondServletTest
    extends MockHttpServletTestSupport<RedirectBindingSecondServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/redirectBindingSecond");
    setServlet(new RedirectBindingSecondServlet());
  }

  @Test
  void testDoGet() throws IOException {
    // 리다이렉트 바인딩시는 address 값은 첫번째 서블릿을 요청했을 때 생성된 request 객체에 설정된 것이고
    // 지금의 request는 브라우저가 새로 생성한 request 객체이므로 설정이 안된 상태이다.
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains("주소: null");
    assertThat(response.getContentAsString()).contains("redirect를 이용한 binding 실습입니다.");
  }
}
