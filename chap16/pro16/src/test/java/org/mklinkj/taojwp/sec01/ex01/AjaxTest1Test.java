package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class AjaxTest1Test extends MockHttpServletTestSupport<AjaxTest1> {

  @Test
  void testDoHandle() throws Exception {
    runGivenWhenThen(
        () -> request.setParameter("param", "Hello, jQuery"), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getContentAsString()).isEqualTo("안녕하세요! 서버입니다.");
        });
  }

  @Override
  protected Class<AjaxTest1> getServletClass() {
    return AjaxTest1.class;
  }

  @Override
  protected String getServletPath() {
    return "/ajaxTest1";
  }
}
