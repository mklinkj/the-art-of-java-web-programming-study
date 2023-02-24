package org.mklinkj.taojwp.sevlet;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class RateServletTest {

  private RateServlet rateServlet;

  private MockHttpServletRequest request;

  private MockHttpServletResponse response;

  @BeforeEach()
  void beforeEach() {
    rateServlet = new RateServlet();
    request = new MockHttpServletRequest();
    request.setContextPath("");
    request.setServletPath("/calc");

    response = new MockHttpServletResponse();
  }

  @Test
  void testDoGet() throws IOException {
    rateServlet.doGet(request, response);
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
              <form name='frmCalc' method='post' action='/calc'>
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

    rateServlet.doPost(request, response);
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
              <a href='/calc'>환율 계산기 </a>
            </body>
            </html>
            """);
  }
}
