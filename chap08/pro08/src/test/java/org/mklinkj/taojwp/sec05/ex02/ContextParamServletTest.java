package org.mklinkj.taojwp.sec05.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class ContextParamServletTest extends MockHttpServletTestSupport<ContextParamServlet> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/cset");
    setServlet(
        new ContextParamServlet() {
          @Override
          public ServletContext getServletContext() {
            return servletContext;
          }
        });
  }

  @Test
  void testDoGet() throws ServletException, IOException {
    // 테스트 코드에서는 실제 web.xml을 읽지 않아서 아래와 같이 설정해 줄 수 밖에 없을 것 같다.
    servlet.getServletContext().setInitParameter("menu_member", "회원등록 회원조회 회원수정");
    servlet.getServletContext().setInitParameter("menu_order", "주문조회 주문등록 주문취소");
    servlet.getServletContext().setInitParameter("menu_goods", "상품조회 상품등록 상품수정 상품삭제");

    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    assertThat(response.getContentAsString()) //
        .contains("회원등록 회원조회 회원수정") //
        .contains("주문조회 주문등록 주문취소")
        .contains("상품조회 상품등록 상품수정 상품삭제");
  }
}
