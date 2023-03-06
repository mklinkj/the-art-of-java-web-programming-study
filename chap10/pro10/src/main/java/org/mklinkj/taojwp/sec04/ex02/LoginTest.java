package org.mklinkj.taojwp.sec04.ex02;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/userCountLogin2")
public class LoginTest extends HttpServlet {

  private final List<String> userList = new CopyOnWriteArrayList<>();

  @SuppressWarnings("unchecked")
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    ServletContext context = getServletContext();

    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();

    String userId = request.getParameter("user_id");
    String userPassword = request.getParameter("user_pw");

    LoginImpl loginUser = new LoginImpl(userId, userPassword);

    if (session.isNew()) {
      session.setAttribute("loginUser", loginUser);
      userList.add(userId);
      context.setAttribute("userList", userList);
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
              <h3>접속 아이디</h3>
              <ul>
                %s
              </ul>
              <h3>총 접속자수 %d</h3>
              <a href="userCountLogout2?user_id=%s">로그아웃</a>
              <script>
                setTimeout('history.go(0);', 5000)
              </script>
            </body>
            </html>
            """, createUserList((List<String>) context.getAttribute("userList")),
        LoginImpl.totalUserCount, userId
    );
  }

  private String createUserList(List<String> userList) {
    return userList.stream()
        .reduce("", (lis, user) -> lis.concat(String.format("<li>%s</li>", user)));
  }
}
