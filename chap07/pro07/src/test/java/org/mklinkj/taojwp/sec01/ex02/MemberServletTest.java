package org.mklinkj.taojwp.sec01.ex02;

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

  @Test
  void addMemberThenDelAddMember() throws IOException {
    request.setParameter("command", "addMember");
    request.setParameter("id", "mklinkj2");
    request.setParameter("name", "mee");
    request.setParameter("pwd", "1234");
    request.setParameter("email", "123@123.com");
    // 회원 등록 수행: mklinkj2
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).contains("<td>mklinkj2</td>");

    // 회원 삭제 수행
    resetMock(); // 같은 메서드 내에서 서블릿 재접근시는 Mock Request, Response를 재설정 해주는 것이 좋겠다.

    request.setParameter("command", "delMember");
    request.setParameter("id", "mklinkj2");
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).doesNotContain("<td>mklinkj2</td>");
  }
}
