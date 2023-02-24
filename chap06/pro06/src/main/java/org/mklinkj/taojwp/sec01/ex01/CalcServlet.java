package org.mklinkj.taojwp.sec01.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
  private static final float USD_RATE = 1124.70F;
  private static final float JPY_RATE = 10.113F;
  private static final float CNY_RATE = 163.30F;
  private static final float GBP_RATE = 1444.35F;
  private static final float EUR_RATE = 1295.97F;

  private static final String SERVER_ENCODING = StandardCharsets.UTF_8.name();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    doHandle(request, response);
  }

  private String makeRateInputForm(String contextPath, String servletPath) {
    return StringSubstitutor.replace(
        """
          <!DOCTYPE html>
          <html lang="ko">
          <head>
            <meta charset="UTF-8">
            <title>환율 계산기</title>
          </head>
          <body>
            <h1>환율 계산기</h1>
            <form name='frmCalc' method='post' action='${contextPath}${servletPath}'>
              원화: <input type='text' name='won' size=10 value='0' />
              <select name='operator'>
                <option value='dollar'>달러</option>
                <option value='en'>엔화</option>
                <option value='wian'>위안</option>
                <option value='pound'>파운드</option>
                <option value='euro'>유로</option>
              </select>
              <input type='hidden' name='command' value='calculate' />
              <input type='submit' value='변환' />
            </form>
          </body>
          </html>
          """,
        Map.of("contextPath", contextPath, "servletPath", servletPath));
  }

  private String makeRateResult(String result, String contextPath, String servletPath) {
    return StringSubstitutor.replace(
        """
          <!DOCTYPE html>
          <html lang="ko">
          <head>
            <meta charset="UTF-8">
            <title>환율 계산기</title>
          </head>
          <body>
            <h1>변환결과</h1>
            <h1>${result}</h1>
            <a href='${contextPath}${servletPath}'>환율 계산기 </a>
          </body>
          </html>
          """,
        Map.of("result", result, "contextPath", contextPath, "servletPath", servletPath));
  }

  private void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(String.format("text/html; charset=%s", SERVER_ENCODING));
    PrintWriter pw = response.getWriter();
    String command = request.getParameter("command");
    String won = request.getParameter("won");
    String operator = request.getParameter("operator");

    if ("calculate".equals(command)) {
      String result = calculate(Float.parseFloat(won), operator);
      pw.print(makeRateResult(result, request.getContextPath(), request.getServletPath()));
      return;
    }

    pw.print(makeRateInputForm(request.getContextPath(), request.getServletPath()));
    pw.close();
  }

  private static String calculate(float won, String operator) {
    return switch (operator) {
      case "dollar" -> String.format("%.6f", won / USD_RATE);
      case "en" -> String.format("%.6f", won / JPY_RATE);
      case "wian" -> String.format("%.6f", won / CNY_RATE);
      case "pound" -> String.format("%.6f", won / GBP_RATE);
      case "euro" -> String.format("%.6f", won / EUR_RATE);
      default -> null;
    };
  }
}
