package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class AjaxTest2Test extends MockHttpServletTestSupport<AjaxTest2> {

  @Test
  void testDoHandle() throws Exception {
    runGivenWhenThen(
        () -> {}, //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getContentAsString()) //
              .contains("스마일1 책")
              .contains("스마일1 저자")
              .contains("스마일2 책")
              .contains("스마일2 저자");
        });
  }

  @Override
  protected Class<AjaxTest2> getServletClass() {
    return AjaxTest2.class;
  }

  @Override
  protected String getServletPath() {
    return "/ajaxTest2";
  }
}
