package org.mklinkj.taojwp.sec06.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class InitParamServletTest extends MockHttpServletTestSupport<InitParamServlet> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/sInit");
    setServlet(
        new InitParamServlet() {
          @Override
          public ServletContext getServletContext() {
            return servletContext;
          }

          @Override
          public ServletConfig getServletConfig() {
            return servletConfig;
          }
        });
  }

  @Test
  void testDoGet() throws ServletException, IOException {
    // 어노테이션 프로세서 같은 것이 해줘야 할 일인데..
    // 현재 테스트 환경에서는 그렇게 할 수가 없어서 수동으로 넣어준다.
    servletConfig.addInitParameter("email", "admin@jweb.com");
    servletConfig.addInitParameter("tel", "010-1111-2222");

    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    assertThat(response.getContentAsString()) //
        .contains("admin@jweb.com") //
        .contains("010-1111-2222");
  }
}
