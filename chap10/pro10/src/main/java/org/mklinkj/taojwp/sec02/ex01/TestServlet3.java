package org.mklinkj.taojwp.sec02.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("*.do") // 앞에 '/' 붙이지 않는 것이 주의
//@WebServlet("/*") // 모든 요청 URL 패턴, 그래도 경로와 완전일치가 매핑하는게 있으면 그것이 우선이다.
public class TestServlet3 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    String context = request.getContextPath(); // 컨텍스트 이름만
    String url = request.getRequestURL().toString(); // 전체 URL
    String mapping = request.getServletPath(); // 서블릿 매핑 이름
    String uri = request.getRequestURI(); // URI

    out.printf("""
        <!DOCTYPE html>
        <html lang="ko">
        <head>
          <meta charset="UTF-8">
          <title>%s</title>
          <style>
            body {
              background-color:%s
            }
          </style>
        </head>
        <body>
          <h3>컨텍스트 이름: %s</h3>
          <h3>전체 경로: %s</h3>
          <h3>매핑 이름: %s</h3>
          <h3>URI: %s</h3>
        </body>
        </html>
        """, getClass().getSimpleName(), "red", context, url, mapping, uri);
  }
}
