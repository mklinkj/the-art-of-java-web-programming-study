package org.mklinkj.taojwp.sec01.ex01;

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
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
  private static final String UTF_8_ENCODING = StandardCharsets.UTF_8.name();

  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(String.format("text/html;charset=%s", UTF_8_ENCODING));

    PrintWriter out = response.getWriter();

    String userId = request.getParameter("user_id");
    String password = request.getParameter("user_pw");

    out.print(
        StringSubstitutor.replace(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 정보</title>
            </head>
            <body>
              <h4>아이디: ${id}</h4>
              <h4>패스워드: ${pw}</h4>
            </body>
            </html>
            """,
            Map.of("id", userId, "pw", password)));
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 메서드 호출");
  }
}
