package org.mklinkj.taojwp.sec01.ex02;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/first/*")
public class TestServlet2 extends HttpServlet {

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
              background-color:%s; 
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
        """, getClass().getSimpleName(), "yellow", context, url, mapping, uri);
  }
}
