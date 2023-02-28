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

class SetServletContextTest extends MockHttpServletTestSupport<SetServletContext> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/cset");
    setServlet(
        new SetServletContext() {
          @Override
          public ServletContext getServletContext() {
            return servletContext;
          }
        });
  }

  @SuppressWarnings("unchecked")
  @Test
  void testDoGet() throws ServletException, IOException {
    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    Object objList = request.getServletContext().getAttribute("member");
    assertThat(objList).isNotNull();

    assertThat((List<Object>) objList).containsExactly("이순신", 30);
  }
}
