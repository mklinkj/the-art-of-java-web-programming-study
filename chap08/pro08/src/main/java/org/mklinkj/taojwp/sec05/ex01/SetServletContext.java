package org.mklinkj.taojwp.sec05.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cset")
public class SetServletContext extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    ServletContext context = getServletContext();

    List<Object> member = new ArrayList<>();
    member.add("이순신");
    member.add(30);

    context.setAttribute("member", member);

    out.print(
        """
          <!DOCTYPE html>
          <html lang="ko">
          <head>
            <meta charset="UTF-8">
            <title>ServletContext 테스트</title>
          </head>
          <body>
            <h4>이순신과 30 설정</h4>
          </body>
          </html>
            """);
  }
}
