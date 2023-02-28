package org.mklinkj.taojwp.sec01.ex04;

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

@WebServlet("/dispatchBindingSecond")
public class DispatchBindingSecondServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    Object address = request.getAttribute("address");
    address =
        (address == null)
            ? "null"
            : address; // StringSubstitutor.replace() 함수를 썼기 때문에 null일 경우라도 문자열로 치환해줘야한다.
    out.print(
        StringSubstitutor.replace(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>dispatch 포워딩시 바인딩 실습</title>
            </head>
            <body>
              <h4>주소: ${address}</h4>
              <h4>dispatch를 이용한 binding 실습입니다.</h4>
            </body>
            </html>
            """,
            Map.of("address", address)));
  }
}
