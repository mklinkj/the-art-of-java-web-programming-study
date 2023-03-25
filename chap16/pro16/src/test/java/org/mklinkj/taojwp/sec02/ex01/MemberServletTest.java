package org.mklinkj.taojwp.sec02.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class MemberServletTest extends MockHttpServletTestSupport<MemberServlet> {

  @Test
  void testDoHandle_exist_Id() throws Exception {
    runGivenWhenThen(
        () -> request.setParameter("id", "mklinkj"), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getContentAsString()) //
              .contains("not_usable");
        });
  }

  @Test
  void testDoHandle_not_exist_Id() throws Exception {
    runGivenWhenThen(
        () -> request.setParameter("id", "mklinkj2"), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getContentAsString()) //
              .contains("usable");
        });
  }

  @Override
  protected Class<MemberServlet> getServletClass() {
    return MemberServlet.class;
  }

  @Override
  protected String getServletPath() {
    return "/mem";
  }
}
