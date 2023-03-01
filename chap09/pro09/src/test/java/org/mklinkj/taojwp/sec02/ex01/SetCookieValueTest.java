package org.mklinkj.taojwp.sec02.ex01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.FixedDateTestHelper;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class SetCookieValueTest extends MockHttpServletTestSupport<SetCookieValue> {

  @Test
  void testDoGet() throws IOException {
    FixedDateTestHelper.changeNowLocalDateTime(
        LocalDateTime.of(2023, 3, 1, 0, 0, 0, 0), //
        () -> servlet.doGet(request, response));

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    assertThat(response.getCookie("cookieTest").getValue()) //
        .isEqualTo(URLEncoder.encode("JSP프로그래밍입니다.", SERVER_ENCODING));

    assertThat(response.getContentAsString())
        .contains("현재시간: 2023-03-01") //
        .contains("문자열을 Cookie에 저장합니다.");
  }

  @Override
  protected Class<SetCookieValue> getServletClass() {
    return SetCookieValue.class;
  }

  @Override
  protected String getServletPath() {
    return "/set";
  }
}
