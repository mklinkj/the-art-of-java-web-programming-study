package org.mklinkj.taojwp.sec04.ex03;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;

class MemberServletTest extends MockHttpServletTestSupport<MemberServlet> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/member");
    setServlet(new MemberServlet());
  }

  @Test
  void testViewMembers() throws IOException, ServletException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getForwardedUrl()).isEqualTo("viewMembers");
    assertThat(request.getAttribute("memberList")).isNotNull();
  }

  @Test
  void testDeleteThenAddMember() throws IOException, ServletException {
    request.setParameter("command", "delMember");
    request.setParameter("id", "mklinkj2");
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getForwardedUrl()).isEqualTo("viewMembers");

    resetMock();

    request.setParameter("command", "addMember");
    request.setParameter("id", "mklinkj2");
    request.setParameter("name", "mee");
    request.setParameter("pwd", "1234");
    request.setParameter("email", "123@123.com");
    // 회원 등록 수행: mklinkj2
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getForwardedUrl()).isEqualTo("viewMembers");

    List<MemberVO> memberList = getMemberList(request);

    Optional<MemberVO> savedVO =
        memberList.stream().filter(member -> "mklinkj2".equals(member.getId())).findFirst();

    assertThat(savedVO.isPresent()).isTrue();
    assertThat(savedVO.get().getId()).isEqualTo("mklinkj2");
  }

  @SuppressWarnings("unchecked")
  private List<MemberVO> getMemberList(MockHttpServletRequest request) {
    return (List<MemberVO>) request.getAttribute("memberList");
  }
}
