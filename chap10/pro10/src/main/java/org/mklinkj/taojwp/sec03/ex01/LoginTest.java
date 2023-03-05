package org.mklinkj.taojwp.sec03.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/login")
public class LoginTest extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LOGGER.info("인코딩이 설정되지 않았다면 null이다: {}", request.getCharacterEncoding());
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    String userName = request.getParameter("user_name");
    String userPassword = request.getParameter("user_pw");

    out.printf(
        """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 정보</title>
            </head>
            <body>
              <h3>이름은 %s</h3>
              <h3>비밀번호는 %s</h3>
            </body>
            </html>
            """, userName, userPassword
    );
  }
}
