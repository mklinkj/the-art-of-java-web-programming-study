package org.mklinkj.taojwp.sec05.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

class ShowMemberTest extends MockHttpServletTestSupport<ShowMember> {
  @Test
  void testLoginViewSuccess() throws Exception {
    runGivenWhenThen(
        () -> {
          MockHttpSession session = new MockHttpSession(servletContext);
          session.setAttribute("isLogon", true);
          session.setAttribute("login.id", "mklinkj");
          session.setAttribute("login.pwd", "1234");
          request.setSession(session);
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          HttpSession cratedSession = Objects.requireNonNull(request.getSession(false));

          assertThat(cratedSession.getAttribute("isLogon")) //
              .isEqualTo(true);
          assertThat(cratedSession.getAttribute("login.id")) //
              .isEqualTo("mklinkj");
          assertThat(cratedSession.getAttribute("login.pwd")) //
              .isEqualTo("1234");

          assertThat(response.getContentAsString()).contains("아이디: mklinkj").contains("비밀번호: 1234");
        });
  }

  @Test
  void testLoginViewFailure_NoSession() throws Exception {
    runGivenWhenThen(
        () -> {},
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(request.getSession(false)).isNull();
          assertThat(response.getRedirectedUrl()).isEqualTo("login4.html");
        });
  }

  @Test
  void testLoginViewFailure_NoLogon() throws Exception {
    runGivenWhenThen(
        () -> {
          MockHttpSession session = new MockHttpSession(servletContext);
          session.setAttribute("isLogon", false);
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(request.getSession(false)).isNull();
          assertThat(response.getRedirectedUrl()).isEqualTo("login4.html");
        });
  }

  @Override
  protected Class<ShowMember> getServletClass() {
    return ShowMember.class;
  }

  @Override
  protected String getServletPath() {
    return "/show";
  }
}
