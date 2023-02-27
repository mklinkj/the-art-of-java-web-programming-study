package org.mklinkj.taojwp.sec04.ex03;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class ViewServletTest extends MockHttpServletTestSupport<ViewServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/viewMembers");
    setServlet(new ViewServlet());
  }

  @Test
  void testDoGet() throws IOException {
    request.setAttribute("memberList", List.of(createTestMember()));
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains("<title>회원 목록</title>");
    assertThat(response.getContentAsString()).contains("<td>mklinkj</td>");
  }

  private MemberVO createTestMember() {
    MemberVO vo = new MemberVO();
    vo.setId("mklinkj");
    vo.setName("개발인");
    vo.setPwd("12324");
    vo.setEmail("123@123.com");
    vo.setJoinDate(LocalDateTime.now());
    return vo;
  }
}
