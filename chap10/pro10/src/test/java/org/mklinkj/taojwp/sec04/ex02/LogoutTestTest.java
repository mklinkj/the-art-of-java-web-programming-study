package org.mklinkj.taojwp.sec04.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class LogoutTestTest extends MockHttpServletTestSupport<LogoutTest> {

  @Test
  void testDoPost() throws Exception {
    runGivenWhenThen(
        () -> {
          List<String> userList = new CopyOnWriteArrayList<>();
          userList.add("아이디01");
          servletContext.setAttribute("userList", userList);

          request.setParameter("user_id", "아이디01");
        },
        () -> servlet.doPost(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

          assertThat(response.getContentAsString()) //
              .contains("아이디01 아이디는 로그아웃 되었습니다.");
        });
  }

  @Override
  protected Class<LogoutTest> getServletClass() {
    return LogoutTest.class;
  }

  @Override
  protected String getServletPath() {
    return "/userCountLogout2";
  }
}