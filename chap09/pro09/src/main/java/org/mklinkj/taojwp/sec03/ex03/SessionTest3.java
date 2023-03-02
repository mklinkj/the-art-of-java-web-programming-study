package org.mklinkj.taojwp.sec03.ex03;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sess3")
public class SessionTest3 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession();
    out.printf("세션 아이디: %s<br>%n", session.getId());
    out.printf("최초 세션 생성 시각: %s<br>%n", session.getCreationTime());
    out.printf("최초 세션 접근 시각: %s<br>%n", session.getLastAccessedTime());
    out.printf("기본 세션 유효 시간: %s<br>%n", session.getMaxInactiveInterval());
    out.printf("세션 유효 시간: %s<br>%n", session.getMaxInactiveInterval());
    if (session.isNew()) {
      out.print("새 세션이 만들어졌습니다.");
    }
    session.invalidate();
  }
}
