package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class SetAttributeTest extends MockHttpServletTestSupport<SetAttribute> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(servletContext.getAttribute("context")) //
              .isEqualTo("context에 바인딩 됩니다.");
          assertThat(request.getSession(false).getAttribute("session")) //
              .isEqualTo("session에 바인딩 됩니다.");
          assertThat(request.getAttribute("request")) //
              .isEqualTo("request에 바인딩 됩니다.");
        });
  }

  @Override
  protected Class<SetAttribute> getServletClass() {
    return SetAttribute.class;
  }

  @Override
  protected String getServletPath() {
    return "/set";
  }
}
