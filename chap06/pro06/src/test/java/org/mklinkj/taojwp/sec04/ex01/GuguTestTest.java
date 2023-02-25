package org.mklinkj.taojwp.sec04.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class GuguTestTest extends MockHttpServletTestSupport<GuguTest> {

  @BeforeEach
  void beforeEach() {
    request.setServletPath("/guguTest");
    setServlet(new GuguTest());
  }

  @Test
  void testGuGuDanList() throws IOException {
    request.setParameter("dan", "2");
    servlet.doGet(request, response);

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .isEqualTo(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>구구단 목록</title>
              <style>
                table {
                  width: 800px;
                }
                table, th, td {
                  border: 1px solid black;
                  text-align: center;
                }
              </style>
            </head>
            <body>
              <table>
                <tbody>
                  <tr style="background-color:#ffff66">
                    <th colspan="2">2단 출력</th>
                  </tr>
                  <tr style="background-color:#81bef7">
                    <td style="width:400px">2 * 1</td><td style="width:400px">2</td>
                  </tr>
                  <tr style="background-color:#acfa58">
                    <td style="width:400px">2 * 2</td><td style="width:400px">4</td>
                  </tr>
                  <tr style="background-color:#81bef7">
                    <td style="width:400px">2 * 3</td><td style="width:400px">6</td>
                  </tr>
                  <tr style="background-color:#acfa58">
                    <td style="width:400px">2 * 4</td><td style="width:400px">8</td>
                  </tr>
                  <tr style="background-color:#81bef7">
                    <td style="width:400px">2 * 5</td><td style="width:400px">10</td>
                  </tr>
                  <tr style="background-color:#acfa58">
                    <td style="width:400px">2 * 6</td><td style="width:400px">12</td>
                  </tr>
                  <tr style="background-color:#81bef7">
                    <td style="width:400px">2 * 7</td><td style="width:400px">14</td>
                  </tr>
                  <tr style="background-color:#acfa58">
                    <td style="width:400px">2 * 8</td><td style="width:400px">16</td>
                  </tr>
                  <tr style="background-color:#81bef7">
                    <td style="width:400px">2 * 9</td><td style="width:400px">18</td>
                  </tr>
                </tbody>
              </table>
              <a href="/pro06/test01/gugu.html">돌아가기...</a>
            </body>
            </html>
            """);
  }
}
