package org.mklinkj.taojwp.sec01.ex04;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class DispatchBindingSecondServletTest
    extends MockHttpServletTestSupport<DispatchBindingSecondServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/dispatchBindingSecond");
    setServlet(new DispatchBindingSecondServlet());
  }

  @Test
  void testDoGet() throws IOException {
    // 디스패치 바인딩시는 address 값은 첫번째 서블릿을 요청했을 때 생성된,
    // request 객체에 설정된 것을 그대로 두번째 서블릿에 전달하므로 request 객체에 주소 값이 있다.
    request.setAttribute("address", "서울시 성북구");
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains("주소: 서울시 성북구");
    assertThat(response.getContentAsString()).contains("dispatch를 이용한 binding 실습입니다.");
  }
}
