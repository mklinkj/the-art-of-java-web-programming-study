package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    String forwardingType = request.getParameter("forwardingType");
    String name = request.getParameter("name");

    PrintWriter out = response.getWriter();

    out.print(
        StringSubstitutor.replace(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>포워딩 실습</title>
            </head>
            <body>
              <h4>${forwardingType}를 이용한 redirect 실습입니다.</h4>
              <h4>이름: ${name}</h4>
            </body>
            </html>
            """,
            Map.of("forwardingType", forwardingType, "name", name)));
  }
}
