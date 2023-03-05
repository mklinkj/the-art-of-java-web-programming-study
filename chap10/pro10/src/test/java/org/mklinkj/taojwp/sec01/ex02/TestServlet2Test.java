package org.mklinkj.taojwp.sec01.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class TestServlet2Test extends MockHttpServletTestSupport<TestServlet2> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setRequestURI("/pro10/first/a");
          request.setServerPort(8090);
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(response.getContentAsString()) //
              .contains("컨텍스트 이름: /pro10")
              .contains("전체 경로: http://localhost:8090/pro10/first/a")
              .contains("매핑 이름: /first")
              .contains("URI: /pro10/first/a")
              .contains("background-color:yellow");
        });
  }

  @Override
  protected Class<TestServlet2> getServletClass() {
    return TestServlet2.class;
  }

  @Override
  protected String getServletPath() {
    return "/first/test";
  }
}
