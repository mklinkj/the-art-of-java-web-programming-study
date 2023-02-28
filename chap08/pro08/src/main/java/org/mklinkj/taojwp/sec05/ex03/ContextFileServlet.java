package org.mklinkj.taojwp.sec05.ex03;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.text.StringSubstitutor;

@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    ServletContext context = getServletContext();

    String menu;
    String menuMember = "null";
    String menuOrder = "null";
    String menuGoods = "null";
    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(getClass().getResourceAsStream("/WEB-INF/bin/init.txt"), SERVER_ENCODING))) {

      while ((menu = br.readLine()) != null) {
        StringTokenizer tokens = new StringTokenizer(menu, ",");
        menuMember = tokens.nextToken();
        menuOrder = tokens.nextToken();
        menuGoods = tokens.nextToken();
      }
    }

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
                    <th>메뉴 이름</th>
                  </tr>
                </thead>
                <tbody>${tableContent}</tbody>
              </table>
              </script>
            </body>
            </html>
            """,
            Map.of("tableContent", createTableContent(menuMember, menuOrder, menuGoods))));
  }

  private String createTableContent(String menuMember, String menuOrder, String menuGoods) {

    return StringSubstitutor.replace(
        """
        <tr>
          <td>${menuMember}</td>
        </tr>
        <tr>
          <td>${menuOrder}</td>
        </tr>
        <tr>
          <td>${menuGoods}</td>
        </tr>
        """,
        Map.of("menuMember", menuMember, "menuOrder", menuOrder, "menuGoods", menuGoods));
  }
}
