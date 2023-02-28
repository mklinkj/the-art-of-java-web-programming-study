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
import java.util.List;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

@WebServlet("/cget")
public class GetServletContext extends HttpServlet {

  @SuppressWarnings("unchecked")
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    ServletContext context = getServletContext();

    List<Object> list = (List<Object>) context.getAttribute("member");

    String name = (String) list.get(0);
    int age = (int) list.get(1);

    out.print(
        StringSubstitutor.replace(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>ServletContext 테스트</title>
            </head>
            <body>
              <h4>이름: ${name}</h4>
              <h4>나이: ${age}</h4>
            </body>
            </html>
              """,
            Map.of("name", name, "age", age)));
  }
}
