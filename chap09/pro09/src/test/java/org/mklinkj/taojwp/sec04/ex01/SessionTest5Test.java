package org.mklinkj.taojwp.sec04.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class SessionTest5Test extends MockHttpServletTestSupport<SessionTest5> {
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

          assertThat(response.getContentAsString())
              .containsPattern("blockCookieSessionLogin")
              // MockHttpServletResponse의 encodedURL 메서드는 입력받은 URL을 그대로 반환하게 구현되어있다.
              // .containsPattern("blockCookieSessionLogin?jsessionid=\\d+")
              .contains("로그인 상태 확인");
        });
  }

  @Override
  protected Class<SessionTest5> getServletClass() {
    return SessionTest5.class;
  }

  @Override
  protected String getServletPath() {
    return "/blockCookieSessionLogin";
  }
}
