package org.mklinkj.taojwp.sevlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/** Servlet implementation class RateServlet */
@WebServlet(urlPatterns = "/calc")
public class RateServlet extends HttpServlet {
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

  private void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(String.format("text/html; charset=%s", SERVER_ENCODING));
    PrintWriter pw = response.getWriter();
    String command = request.getParameter("command");
    String won = request.getParameter("won");
    String operator = request.getParameter("operator");
    final String contextPath = request.getContextPath();

    if ("calculate".equals(command)) {
      String result = calculate(Float.parseFloat(won), operator);
      pw.print("<html><h1>변환결과</h1><br>");
      pw.print("<html><h1>" + result + "</h1><br><br><br>");
      pw.print(String.format("<a href='%s/calc'>환율 계산기 </a>", contextPath));
      return;
    }

    pw.print("<html><title>환율계산기</title>");
    pw.print("<h1>환율 계산기</h1><br>");
    pw.print(
        String.format("<form  name='frmCalc' method='post' action='%s/calc' />  ", contextPath));
    pw.print("원화: <input type='text' name='won' size=10 value='0'  />  ");
    pw.print("<select name='operator' >");
    pw.print("<option value='dollar'>달러</option>");
    pw.print("<option value='en'>엔화</option>");
    pw.print("<option value='wian'>위안</option>");
    pw.print("<option value='pound'>파운드</option>");
    pw.print("<option value='euro'>유로</option>");
    pw.print("</select>");
    pw.print("<input type='hidden' name='command' value='calculate'  />  ");
    pw.println("<input type='submit' value='변환'  />");
    pw.println("</form>");
    pw.print("</html>");
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
