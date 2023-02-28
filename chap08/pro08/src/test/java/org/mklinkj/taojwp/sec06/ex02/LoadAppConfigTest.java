package org.mklinkj.taojwp.sec06.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoadAppConfigTest extends MockHttpServletTestSupport<LoadAppConfig> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/loadConfig");
    setServlet(
        new LoadAppConfig() {
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
    // 테스트 코드에서는 실제 web.xml을 읽지 않아서 아래와 같이 설정해 줄 수 밖에 없을 것 같다.
    servletContext.addInitParameter("menu_member", "회원등록 회원조회 회원수정");
    servletContext.addInitParameter("menu_order", "주문조회 주문등록 주문취소");
    servletContext.addInitParameter("menu_goods", "상품조회 상품등록 상품수정 상품삭제");

    // init()도 컨테이너가 해줘야지만 테스트에서는 직접 실행해줘야겠다.
    servlet.init(servletConfig);

    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    assertThat(response.getContentAsString()) //
        .contains("회원등록 회원조회 회원수정") //
        .contains("주문조회 주문등록 주문취소")
        .contains("상품조회 상품등록 상품수정 상품삭제");
  }
}
