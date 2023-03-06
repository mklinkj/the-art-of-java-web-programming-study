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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/userCountLogout2")
public class LogoutTest extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doHandle(request, response);
  }

  @SuppressWarnings("unchecked")
  private void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    ServletContext context = getServletContext();

    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();

    String userId = request.getParameter("user_id");
    session.invalidate();

    List<String> userList = (List<String>) context.getAttribute("userList");
    userList.remove(userId);
    // userList가 컨텍스트에 존재하는 동일한 참조인데.. 참조만 지웠다가 재할당하는 부분이 필요한 부분인지?
    // 새로 복사해서 만들었다면 재할당하는게 맞긴한데...
    // context.removeAttribute("userList");
    // context.setAttribute("userList", userList);

    out.printf("""
        <!DOCTYPE html>
        <html lang="ko">
        <head>
          <meta charset="UTF-8">
          <title>로그아웃 페이지</title>
        </head>
        <body>
          <h3>%s 아이디는 로그아웃 되었습니다.</h3>
          <a href="login2.html">로그인으로 돌아가기...</a>
        </body>
        </html>
        """, userId
    );
  }
}
