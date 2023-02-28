package org.mklinkj.taojwp.sec05.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class GetServletContextTest extends MockHttpServletTestSupport<GetServletContext> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/cget");
    setServlet(
        new GetServletContext() {
          @Override
          public ServletContext getServletContext() {
            return servletContext;
          }
        });
  }

  @Test
  void testDoGet() throws ServletException, IOException {
    request.getServletContext().setAttribute("member", List.of("이순신", 30));
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains("<h4>이름: 이순신</h4>");
    assertThat(response.getContentAsString()).contains("<h4>나이: 30</h4>");
  }
}
