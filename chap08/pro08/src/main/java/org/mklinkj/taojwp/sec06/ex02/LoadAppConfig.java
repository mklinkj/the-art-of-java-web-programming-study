package org.mklinkj.taojwp.sec06.ex02;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;

@Slf4j
@WebServlet(name = "loadConfig", value = "/loadConfig", loadOnStartup = 1)
public class LoadAppConfig extends HttpServlet {

  private ServletContext context;

  @Override
  public void init(ServletConfig config) throws ServletException {
    LOGGER.info("LoadAppConfig의 init 메서드 호출");
    context = config.getServletContext();

    String menuMember = context.getInitParameter("menu_member");
    String menuOrder = context.getInitParameter("menu_order");
    String menuGoods = context.getInitParameter("menu_goods");

    context.setAttribute("menu_member", menuMember);
    context.setAttribute("menu_order", menuOrder);
    context.setAttribute("menu_goods", menuGoods);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    String menuMember = context.getInitParameter("menu_member");
    String menuOrder = context.getInitParameter("menu_order");
    String menuGoods = context.getInitParameter("menu_goods");

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
