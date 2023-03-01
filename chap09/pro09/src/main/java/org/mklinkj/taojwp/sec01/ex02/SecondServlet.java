package org.mklinkj.taojwp.sec01.ex02;

import static org.mklinkj.taojwp.common.CommonUtils.ifNullToNullString;
import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

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
@WebServlet("/second")
public class SecondServlet extends HttpServlet {

  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    String userId = request.getParameter("user_id");
    String userPw = request.getParameter("user_pw");
    String userAddress = request.getParameter("user_address");

    out.print(
        StringSubstitutor.replace(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>두번째 서블릿</title>
            </head>
            <body>
              ${content}
            </body>
            </html>
            """,
            Map.of(
                "content", createContent(userId, userPw, userAddress, request.getContextPath()))));
  }

  private String createContent(
      String userId, String userPw, String userAddress, String contextPath) {
    if (userId == null || userId.isBlank()) {
      return StringSubstitutor.replace(
          """
              <h4>로그인 하지 않았습니다.</h4>
              다시 로그인 하세요<br>
              <a href="${contextPath}/login.html">로그인창으로 이동하기</a>
              """,
          Map.of("contextPath", contextPath));
    } else {
      return StringSubstitutor.replace(
          """
              <h4>이미 로그인 상태입니다.</h4>
              첫번째 서블릿에서 넘겨준 아이디: ${userId}<br>
              첫번째 서블릿에서 넘겨준 비밀번호: ${userPw}<br>
              첫번째 서블릿에서 넘겨준 주소: ${userAddress}<br>
              """,
          Map.of(
              "userId",
              ifNullToNullString(userId),
              "userPw",
              ifNullToNullString(userPw),
              "userAddress",
              ifNullToNullString(userAddress)));
    }
  }
}
