package org.mklinkj.taojwp.sec04.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;

@Slf4j
@WebServlet("/guguTest")
public class GuguTest extends HttpServlet {

  private static final String SERVER_ENCODING = StandardCharsets.UTF_8.name();

  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(String.format("text/html;charset=%s", SERVER_ENCODING));

    PrintWriter out = response.getWriter();

    int dan = Integer.parseInt(request.getParameter("dan"));

    out.print(
        StringSubstitutor.replace(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>구구단 목록</title>
              <style>
                table {
                  width: 800px;
                }
                table, th, td {
                  border: 1px solid black;
                  text-align: center;
                }
              </style>
            </head>
            <body>
              <table>
                <tbody>
                  <tr style="background-color:#ffff66">
                    <th colspan="2">${dan}단 출력</th>
                  </tr>
            ${tableContent}    </tbody>
              </table>
              <a href="${contextPath}/test01/gugu.html">돌아가기...</a>
            </body>
            </html>
            """,
            Map.of(
                "dan",
                dan,
                "tableContent",
                tableContent(dan),
                "contextPath",
                request.getContextPath())));
  }

  private String tableContent(int dan) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < 10; i++) {
      String trBgColor = ((i % 2) == 0) ? "#acfa58" : "#81bef7";
      sb.append(
          StringSubstitutor.replace(
              """
                          <tr style="background-color:${trBgColor}">
                            <td style="width:400px">${dan} * ${i}</td><td style="width:400px">${result}</td>
                          </tr>
                    """,
              Map.of("dan", dan, "i", i, "result", dan * i, "trBgColor", trBgColor)));
    }
    return sb.toString();
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 메서드 호출");
  }
}
