package org.mklinkj.taojwp.sec01.ex03;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class DispatchForwardFirstServletTest
    extends MockHttpServletTestSupport<DispatchForwardFirstServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/dispatchForwardFirst");
    setServlet(new DispatchForwardFirstServlet());
  }

  @Test
  void testDoGet() throws IOException, ServletException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getForwardedUrl()).isEqualTo("dispatchForwardSecond");
    assertThat(response.getContentAsString())
        .isEqualTo("")
        .describedAs("디스패치 포워딩이 설정되어있는 서블릿을 요청하는 테스트에서 여기서는 대상 서블릿 호출 결과까지 나타내진 않는다.");
  }
}
