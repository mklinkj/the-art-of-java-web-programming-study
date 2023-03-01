package org.mklinkj.taojwp.sec02.ex01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.http.Cookie;
import java.net.URLEncoder;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class GetCookieValueTest extends MockHttpServletTestSupport<GetCookieValue> {

  @Test
  void testDoGet() throws Exception {
    runGivenWhenThen(
        () ->
            request.setCookies(
                new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍입니다.", SERVER_ENCODING))),
        () -> servlet.doGet(request, response),
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getContentAsString()).contains("Cookie값 가져오기: JSP프로그래밍입니다.");
        });
  }

  @Override
  protected Class<GetCookieValue> getServletClass() {
    return GetCookieValue.class;
  }

  @Override
  protected String getServletPath() {
    return "/get";
  }
}
