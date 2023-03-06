package org.mklinkj.taojwp.sec04.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/userCountlogin")
public class LoginTest extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LOGGER.info("인코딩이 설정되지 않았다면 null이다: {}", request.getCharacterEncoding());
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();

    String userId = request.getParameter("user_id");
    String userPassword = request.getParameter("user_pw");

    LoginImpl loginUser = new LoginImpl(userId, userPassword);

    if (session.isNew()) {
      session.setAttribute("loginUser", loginUser);
    }

    out.printf(
        """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 정보</title>
            </head>
            <body>
              <h3>아이디는 %s</h3>
              <h3>총 접속자수 %d</h3>
              <script>
                setTimeout('history.go(0);', 5000)
              </script>
            </body>            
            </html>
            """, userId, LoginImpl.totalUserCount
    );
  }
}
