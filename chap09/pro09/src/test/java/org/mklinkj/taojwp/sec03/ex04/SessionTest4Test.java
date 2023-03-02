package org.mklinkj.taojwp.sec03.ex04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

@Slf4j
class SessionTest4Test extends MockHttpServletTestSupport<SessionTest4> {
  @Test
  void testFirstLoginSuccess() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("user_id", "mklinkj");
          request.setParameter("user_pw", "1234");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(Objects.requireNonNull(request.getSession(false)).getAttribute("user_id")) //
              .isEqualTo("mklinkj");

          assertThat(response.getContentAsString()).containsPattern("로그인 상태 확인");
        });
  }

  @Test
  void testFirstLoginFailure() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setParameter("user_id", "");
          request.setParameter("user_pw", "1234");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(request.getSession(false)) // session.invalidate(); 하기 때문에 존재하는 세션 자체가 없다.
              .isNull();

          assertThat(response.getContentAsString()).containsPattern("다시 로그인 하세요!!");
        });
  }

  @Test
  void testSessionLoginSuccess() throws Exception {
    runGivenWhenThen(
        () -> {
          MockHttpSession session = new MockHttpSession(servletContext);
          session.setAttribute("user_id", "mklinkj");
          request.setSession(session);
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(Objects.requireNonNull(request.getSession(false)).getAttribute("user_id")) //
              .isEqualTo("mklinkj");

          assertThat(response.getContentAsString()).contains("안녕하세요 mklinkj님!!!");
        });
  }

  @Test
  void testSessionLoginFailure() throws Exception {
    runGivenWhenThen(
        () -> {
          MockHttpSession session = new MockHttpSession(servletContext);
          request.setSession(session);
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(request.getSession(false)) // session.invalidate(); 하기 때문에 존재하는 세션 자체가 없다.
              .isNull();

          assertThat(response.getContentAsString()).contains("다시 로그인 하세요!!");
        });
  }

  @Override
  protected Class<SessionTest4> getServletClass() {
    return SessionTest4.class;
  }

  @Override
  protected String getServletPath() {
    return "/sessionLogin";
  }
}
