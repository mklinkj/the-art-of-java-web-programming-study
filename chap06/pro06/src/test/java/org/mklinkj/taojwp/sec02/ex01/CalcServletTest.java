package org.mklinkj.taojwp.sec02.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class CalcServletTest extends MockHttpServletTestSupport<CalcServlet> {

  @BeforeEach
  void beforeEach() {
    setServlet(new CalcServlet());
    request.setServletPath("/calc");
  }

  @Test
  void testDoGet() throws IOException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .isEqualTo(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>환율 계산기</title>
            </head>
            <body>
              <h1>환율 계산기</h1>
              <form name='frmCalc' method='post' action='/pro06/calc'>
                원화: <input type='text' name='won' size=10 value='0' />
                <select name='operator'>
                  <option value='dollar'>달러</option>
                  <option value='en'>엔화</option>
                  <option value='wian'>위안</option>
                  <option value='pound'>파운드</option>
                  <option value='euro'>유로</option>
                </select>
                <input type='hidden' name='command' value='calculate' />
                <input type='submit' value='변환' />
              </form>
            </body>
            </html>
            """);
  }

  @Test
  void testDoPost() throws IOException {
    request.setParameter("command", "calculate");
    request.setParameter("operator", "dollar");
    request.setParameter("won", "1200");

    servlet.doPost(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .isEqualTo(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>환율 계산기</title>
            </head>
            <body>
              <h1>변환결과</h1>
              <h1>1.066951</h1>
              <a href='/pro06/calc'>환율 계산기 </a>
            </body>
            </html>
            """);
  }
}
