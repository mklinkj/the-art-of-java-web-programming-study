package org.mklinkj.taojwp.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.constant.Constants.LOGIN_INFO_KEY_NAME;
import static org.mklinkj.taojwp.member.LoginController.CURRENT_VIEW_PATH_FORMAT;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LoginControllerTest extends MockHttpServletTestSupport<LoginController> {
  @Test
  void testDoHandle_loginForm() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setPathInfo("/loginForm.do");
          servlet.init();
        }, //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl())
              .isEqualTo(CURRENT_VIEW_PATH_FORMAT.formatted("/loginForm.jsp"));
        });
  }

  @Test
  void testDoHandle_login() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setPathInfo("/login.do");
          request.setParameter("id", "mklinkj");
          request.setParameter("pwd", "1234");
          servlet.init();
        }, //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(Objects.requireNonNull(request.getSession()).getAttribute(LOGIN_INFO_KEY_NAME))
              .isNotNull();
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()).isEqualTo("/board/listArticles.do");
        });
  }

  @Test
  void testDoHandle_loginFailure() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setPathInfo("/login.do");
          request.setParameter("id", "mklinkj");
          request.setParameter("pwd", "4321");
          servlet.init();
        }, //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(Objects.requireNonNull(request.getSession()).getAttribute(LOGIN_INFO_KEY_NAME))
              .isNull();
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()).isEqualTo(getServletPath() + "/loginForm.do");
        });
  }

  @Override
  protected Class<LoginController> getServletClass() {
    return LoginController.class;
  }

  @Override
  protected String getServletPath() {
    return "/login";
  }
}
