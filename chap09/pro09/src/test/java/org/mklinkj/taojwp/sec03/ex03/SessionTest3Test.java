package org.mklinkj.taojwp.sec03.ex03;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

@Slf4j
class SessionTest3Test extends MockHttpServletTestSupport<SessionTest3> {
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
              .containsPattern("세션 아이디: \\d+<br>") // Mock 환경에서는 에서는 신규 세션 아이디는 1 부터 시작하는 듯.
              .contains("세션 유효 시간: 0") // 이 값의 설정도 컨테이너의 역활, 서블릿이나, 테스트 환경에서 따로 설정하지 않으면 0.
              .contains("새 세션이 만들어졌습니다.");
        });
  }

  @Test
  void testDoGetWithExistingSession() throws Exception {
    runGivenWhenThen(
        () -> {
          // 이렇게 설정한 것 자체가 있는 세션으로 들어오는 것으로 간주되기 때문에.
          // 서블릿 메서드 종료직전 바로 세션을 삭제했을 상황을 재현하기 힘들다.
          MockHttpSession session = new MockHttpSession(servletContext);
          request.setSession(session);
        },
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          // 생성된 세션을 만들어주는 부분은 컨테이너가 따로 트리거 해주는 것 같은데..
          // 태스트 환경 실행에서는 `JSESSIONID`가 쿠키로 설정되지 않는다.
          assertThat(response.getCookie("JSESSIONID")).isNull();

          String contentAsString = response.getContentAsString();

          LOGGER.info("contentAsString:\n{}", contentAsString);

          assertThat(contentAsString)
              .containsPattern("세션 아이디: \\d+<br>") // 세션 ID는 따로 검증하지 않기로함.
              .contains("세션 유효 시간: 0")
              .doesNotContain("새 세션이 만들어졌습니다.");
          // 항상 새 세션이길 기대하는 테스트를 만들 수 없다.
        });
  }

  @Override
  protected Class<SessionTest3> getServletClass() {
    return SessionTest3.class;
  }

  @Override
  protected String getServletPath() {
    return "/sess3";
  }
}
