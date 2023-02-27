package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    out.print(
        """
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <title>포워딩 실습</title>
        </head>
        <body>
          <h4>sendRedirect를 이용한 redirect 실습입니다.</h4>
        </body>
        </html>
        """);
  }
}
