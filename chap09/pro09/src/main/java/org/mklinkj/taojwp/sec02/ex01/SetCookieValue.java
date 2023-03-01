package org.mklinkj.taojwp.sec02.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.time.LocalDateTime;

@WebServlet("/set")
public class SetCookieValue extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    LocalDateTime now = LocalDateTime.now();

    Cookie c = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍입니다.", SERVER_ENCODING));

    // c.setMaxAge(24 * 60 * 60);
    c.setMaxAge(-1);

    response.addCookie(c);

    out.printf("<h4>현재시간: %s</h4>%n", now);
    out.print("<h4>문자열을 Cookie에 저장합니다.<h4>");
  }
}
