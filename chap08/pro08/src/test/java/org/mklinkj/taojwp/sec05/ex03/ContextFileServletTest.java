package org.mklinkj.taojwp.sec05.ex03;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockServletContext;

class ContextFileServletTest extends MockHttpServletTestSupport<ContextFileServlet> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/cfile");
    setServlet(
        new ContextFileServlet() {
          @Override
          public ServletContext getServletContext() {
            return servletContext;
          }
        });
  }

  @Override
  protected MockServletContext createMockServletContext() {
    MockServletContext servletContext = new MockServletContext();

    return servletContext;
  }

  @Test
  void testDoGet() throws ServletException, IOException {
    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    assertThat(response.getContentAsString()) //
        .contains("회원등록 회원조회 회원수정") //
        .contains("주문조회 주문수정 주문취소")
        .contains("상품조회 상품등록 상품수정 상품삭제");
  }
}
