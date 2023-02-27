package org.mklinkj.taojwp.sec01.ex03;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

@WebServlet("/dispatchForwardSecond")
public class DispatchForwardSecondServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    String name = request.getParameter("name");
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
          <h4>이름: ${name}</h4>
          <h4>dispatch를 이용한 forward 실습입니다.</h4>
        </body>
        </html>
        """,
            Map.of("name", name)));
  }
}
