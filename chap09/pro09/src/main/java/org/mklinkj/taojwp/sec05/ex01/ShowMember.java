package org.mklinkj.taojwp.sec05.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/show")
public class ShowMember extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  private void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(String.format(HTML_CONTENT_TYPE));
    PrintWriter out = response.getWriter();

    String id;
    String pwd;
    Boolean isLogon;

    // 이미 세션이 존재하면 세션을 반환
    // 없으면 null을 반환
    HttpSession session = request.getSession(false);

    if (session != null) {
      Object objIsLogon = session.getAttribute("isLogon");
      isLogon = (objIsLogon == null) ? null : (Boolean) objIsLogon;

      if (isLogon == true) {
        id = (String) session.getAttribute("login.id");
        pwd = (String) session.getAttribute("login.pwd");
        out.printf(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 정보</title>
            </head>
            <body>
              <h4>아이디: %s</h4>
              <h4>비밀번호: %s</h4>
            </body>
            <html>
            """,
            id, pwd);
      } else {
        response.sendRedirect("login4.html");
      }
    } else {
      response.sendRedirect("login4.html");
    }
  }
}
