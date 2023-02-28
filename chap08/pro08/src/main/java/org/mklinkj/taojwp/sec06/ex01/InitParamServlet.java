package org.mklinkj.taojwp.sec06.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

@WebServlet(
    name = "InitParamServlet",
    value = {"/sInit", "/sInit2"},
    initParams = {
      @WebInitParam(name = "email", value = "admin@jweb.com"),
      @WebInitParam(name = "tel", value = "010-1111-2222")
    })
public class InitParamServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    String email = getInitParameter("email");
    String tel = getInitParameter("tel");

    out.print(
        StringSubstitutor.replace(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>initParameter 테스트</title>
              <style>
                table, th, td {
                  border: 1px solid black;
                  text-align: left;
                }
              </style>
            </head>
            <body>
              <table>
                <thead>
                  <tr style="background-color:lightgreen">
                    <th>@WebInitParam 테스트</th>
                  </tr>
                </thead>
                <tbody>${tableContent}</tbody>
              </table>
              </script>
            </body>
            </html>
            """,
            Map.of("tableContent", createTableContent(email, tel))));
  }

  private String createTableContent(String email, String tel) {

    return StringSubstitutor.replace(
        """
        <tr>
          <td>${email}</td>
        </tr>
        <tr>
          <td>${tel}</td>
        </tr>
        """,
        Map.of("email", email, "tel", tel));
  }
}
