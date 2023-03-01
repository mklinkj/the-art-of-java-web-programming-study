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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Optional;

@WebServlet("/get")
public class GetCookieValue extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    Cookie[] allValues = request.getCookies();

    Optional<Cookie> cookie =
        Arrays.stream(allValues).filter(c -> c.getName().equals("cookieTest")).findAny();

    cookie.ifPresent(
        value -> {
          try {
            out.printf(
                "<h2>Cookie값 가져오기: %s</h2>%n",
                URLDecoder.decode(value.getValue(), SERVER_ENCODING));
          } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("✨람다식 내부여서 검사예외를 실행예외로 감싸서 던질 수 밖에 없음.", e);
          }
        });
  }
}
