package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

class GetAttributeTest extends MockHttpServletTestSupport<GetAttribute> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () -> {
          servletContext.setAttribute("context", "context에 바인딩 됩니다.");
          MockHttpSession session = new MockHttpSession(servletContext);
          session.setAttribute("session", "session에 바인딩 됩니다.");
          request.setSession(session);
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(response.getContentAsString()) //
              .contains("context 값: context에 바인딩 됩니다.")
              .contains("session 값: session에 바인딩 됩니다.")
              .doesNotContain("request 값: session에 바인딩 됩니다.");
        });
  }

  @Override
  protected Class<GetAttribute> getServletClass() {
    return GetAttribute.class;
  }

  @Override
  protected String getServletPath() {
    return "/get";
  }
}
