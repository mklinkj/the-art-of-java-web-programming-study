package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class MemberControllerTest extends MockHttpServletTestSupport<MemberController> {

  @Test
  void testDoHandle_exist_Id() throws Exception {
    runGivenWhenThen(
        () -> servlet.init(), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()).isEqualTo("/test01/listMembers.jsp");
        });
  }

  @Override
  protected Class<MemberController> getServletClass() {
    return MemberController.class;
  }

  @Override
  protected String getServletPath() {
    return "/mem";
  }
}
