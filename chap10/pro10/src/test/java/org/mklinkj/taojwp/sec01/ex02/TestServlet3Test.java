package org.mklinkj.taojwp.sec01.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class TestServlet3Test extends MockHttpServletTestSupport<TestServlet3> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setRequestURI("/pro10/a.do");
          request.setServerPort(8090);
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(response.getContentAsString()) //
              .contains("컨텍스트 이름: /pro10")
              .contains("전체 경로: http://localhost:8090/pro10/a.do")
              .contains(
                  "매핑 이름: *.do") // 실제 서버 실행해서는 `매핑 이름: /a.do`으로 나온다. 테스트 결과는 Mock 환경이여서 이런 것 같다.
              .contains("URI: /pro10/a.do")
              .contains("background-color:red");
        });
  }

  @Override
  protected Class<TestServlet3> getServletClass() {
    return TestServlet3.class;
  }

  @Override
  protected String getServletPath() {
    return "*.do";
  }
}
