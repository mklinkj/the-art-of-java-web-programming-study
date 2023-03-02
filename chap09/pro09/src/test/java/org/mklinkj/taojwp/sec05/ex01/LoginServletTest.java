package org.mklinkj.taojwp.sec05.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginServletTest extends MockHttpServletTestSupport<LoginServlet> {
  @Test
  void testLoginSuccess() throws Exception {
    runGivenWhenThen(
        () -> {
          resetDB();
          request.setParameter("user_id", "mklinkj");
          request.setParameter("user_pwd", "1234");
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

          assertThat(response.getContentAsString()).contains("안녕하세요 mklinkj님");
        });
  }

  @Test
  void testLoginFailure() throws Exception {
    runGivenWhenThen(
        () -> {
          resetDB();
          request.setParameter("user_id", "mklinkj");
          request.setParameter("user_pwd", "잘못된_비밀번호");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(request.getSession(false)).isNull();

          assertThat(response.getContentAsString()).contains("로그인 실패").contains("다시 로그인하기");
        });
  }

  @Override
  protected Class<LoginServlet> getServletClass() {
    return LoginServlet.class;
  }

  @Override
  protected String getServletPath() {
    return "/daoSessionLogin";
  }
}
