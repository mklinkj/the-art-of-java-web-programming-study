package org.mklinkj.taojwp.sec04.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;

@Slf4j
@WebServlet("/loginTest2")
public class LoginTest2 extends HttpServlet {
  private static final String SERVER_ENCODING = StandardCharsets.UTF_8.name();

  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(String.format("text/html;charset=%s", SERVER_ENCODING));

    PrintWriter out = response.getWriter();

    String userId = request.getParameter("user_id");
    String password = request.getParameter("user_pw");

    LOGGER.info("아이디: {}", userId);
    LOGGER.info("비밀번호: {}", password);

    if (userId == null || userId.isBlank()) {
      out.print(
          StringSubstitutor.replace(
              """
              <!DOCTYPE html>
              <html lang="ko">
              <head>
                <meta charset="UTF-8">
                <title>로그인 실패</title>
              </head>
              <body>
                <h4>아이디를 입력하세요.</h4>
                <a href="${contextPath}/test01/login.html">로그인 창으로 이동</a>
              </body>
              </html>
              """,
              Map.of("contextPath", request.getContextPath())));

    } else {
      if ("admin".equals(userId)) {
        out.print(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 성공 - 관리자</title>
            </head>
            <body>
              <h4>관리자로 로그인하셨습니다.</h4>
              <input type="button" value='회원정보 수정하기' />
              <input type="button" value='회원정보 삭제하기' />
            </body>
            </html>
            """);
      } else {
        out.print(
            StringSubstitutor.replace(
                """
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                  <meta charset="UTF-8">
                  <title>로그인 성공</title>
                </head>
                <body>
                  <h4>${id}님 로그인하셨습니다.</h4>
                </body>
                </html>
                """,
                Map.of("id", userId, "pw", password)));
      }
    }
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 메서드 호출");
  }
}
