package org.mklinkj.taojwp.sec03.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

class SessionTestTest extends MockHttpServletTestSupport<SessionTest> {
  @Test
  void testDoGetWithNewSession() throws Exception {
    runGivenWhenThen(
        () -> {
          // SessionTest 서블릿에서 getSession()을 할 때 새로 MockHttpSession이 만들어진다.
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          // 생성된 세션을 만들어주는 부분은 컨테이너가 따로 트리거 해주는 것 같은데..
          // 태스트 환경 실행에서는 `JSESSIONID`가 쿠키로 설정되지 않는다.
          assertThat(response.getCookie("JSESSIONID")).isNull();

          assertThat(response.getContentAsString())
              .containsPattern(
                  "세션 아이디: \\d+") // ID 자체는 일치 검증하지 않는게 낫겠다. 테스트를 통체로 돌릴 때 예측할 수 없는 일이 생길 수 있음.
              // 세션 ID는 숫자가 설정되는지 여부만 판단하기로 하자.
              .contains("세션 유효 시간: 0") // 이 값의 설정도 컨테이너의 역활, 서블릿이나, 테스트 환경에서 따로 설정하지 않으면 0.
              .contains("새 세션이 만들어졌습니다.");
        });
  }

  @Test
  void testDoGetWithExistingSession() throws Exception {
    runGivenWhenThen(
        () -> {
          // 이미 세션이 있었다고 간주
          MockHttpSession session = new MockHttpSession(servletContext);
          session.setMaxInactiveInterval(800);
          request.setSession(session);
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          // 생성된 세션을 만들어주는 부분은 컨테이너가 따로 트리거 해주는 것 같은데..
          // 태스트 환경 실행에서는 `JSESSIONID`가 쿠키로 설정되지 않는다.
          assertThat(response.getCookie("JSESSIONID")).isNull();

          assertThat(response.getContentAsString())
              .containsPattern("세션 아이디: \\d+") // 세션 ID는 숫자가 설정되는지 여부만 판단하기로 하자.
              .contains("세션 유효 시간: 800")
              .doesNotContain("새 세션이 만들어졌습니다.");
        });
  }

  @Override
  protected Class<SessionTest> getServletClass() {
    return SessionTest.class;
  }

  @Override
  protected String getServletPath() {
    return "/sess";
  }
}
