package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class MemberServletTest extends MockHttpServletTestSupport<MemberServlet> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/member");
    setServlet(new MemberServlet());
  }

  @Test
  void testMemberList() throws IOException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains("<title>회원 목록</title>");
  }
}
