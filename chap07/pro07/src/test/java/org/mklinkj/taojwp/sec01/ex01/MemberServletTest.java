package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class MemberServletTest extends MockHttpServletTestSupport<MemberServlet> {
  @BeforeEach
  void beforeEach() {
    request.setServletPath("/member");
    setServlet(new MemberServlet());
  }

  @Test
  void testDoGet() throws IOException {
    servlet.doGet(request, response);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    // DB에 저장된 값에 의존하는 테스트
    assertThat(response.getContentAsString())
        .isEqualTo(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>회원 목록</title>
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
                <thead>
                  <tr style="background-color:lightgreen">
                    <th>아이디</th>
                    <th>비밀번호</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>가입일</th>
                  </tr>
                </thead>
                <tbody>
                        <tr>
                    <td>hong</td>
                    <td>1212</td>
                    <td>홍길동</td>
                    <td>hong@gamil.com</td>
                    <td>2023-02-01</td>
                  </tr>
                  <tr>
                    <td>lee</td>
                    <td>1212</td>
                    <td>이순신</td>
                    <td>lee@test.com</td>
                    <td>2023-02-02</td>
                  </tr>
                  <tr>
                    <td>kim</td>
                    <td>1212</td>
                    <td>김유신</td>
                    <td>hong@gamil.com</td>
                    <td>2023-02-03</td>
                  </tr>
            
                </tbody>
              </table>
            </body>
            </html>
            """);
  }
}
