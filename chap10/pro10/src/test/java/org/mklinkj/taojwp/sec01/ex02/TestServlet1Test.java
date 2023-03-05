package org.mklinkj.taojwp.sec01.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;


class TestServlet1Test extends MockHttpServletTestSupport<TestServlet1> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setRequestURI("/pro10/first/test");
          request.setServerPort(8090);
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(response.getContentAsString()) //
              .contains("컨텍스트 이름: /pro10")
              .contains("전체 경로: http://localhost:8090/pro10/first/test")
              .contains("매핑 이름: /first/test")
              .contains("URI: /pro10/first/test")
              .contains("background-color:green");
        });
  }

  @Override
  protected Class<TestServlet1> getServletClass() {
    return TestServlet1.class;
  }

  @Override
  protected String getServletPath() {
    return "/first/test";
  }
}
