package org.mklinkj.taojwp.sec04.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/blockCookieSessionLogin")
public class SessionTest5 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  private void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession();

    String userId = request.getParameter("user_id");

    if (session.isNew()) {
      if (userId == null || userId.isBlank()) {
        out.printf("<a href='login3.html'>다시 로그인 하세요!!</a>%n");
        session.invalidate();
      } else {
        session.setAttribute("user_id", userId);
        String encodedUrl = response.encodeURL("blockCookieSessionLogin");
        out.printf("<a href='%s'>로그인 상태 확인</a>", encodedUrl);
      }
    } else {
      String userIdFromSession = (String) session.getAttribute("user_id");
      if (userIdFromSession == null || userIdFromSession.isBlank()) {
        out.printf("<a href='login3.html'>다시 로그인 하세요!!</a>%n");
        session.invalidate();
      } else {
        out.printf("<h4>안녕하세요 %s님!!!</h4>%n", userIdFromSession);
      }
    }
  }
}
