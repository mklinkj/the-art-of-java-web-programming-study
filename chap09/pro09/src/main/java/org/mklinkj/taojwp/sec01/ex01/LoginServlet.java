package org.mklinkj.taojwp.sec01.ex01;

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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

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
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    String userId = request.getParameter("user_id");
    String userPw = request.getParameter("user_pw");
    String userAddress = request.getParameter("user_address");
    String userEmail = request.getParameter("user_email");
    String userHp = request.getParameter("user_hp");

    out.print(
        StringSubstitutor.replace(
            """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
              <meta charset="UTF-8">
              <title>로그인 정보</title>
            </head>
            <body>
              <h4>안녕하세요!<br>로그인하셨습니다.</h4>
              아이디: ${userId}<br>
              비밀번호: ${userPw}<br>
              주소: ${userAddress}<br>
              email: ${userEmail}<br>
              휴대 전화: ${userHp}<br>
            </body>
            </html>
            """,
            Map.of(
                "userId",
                ifNullToNullString(userId),
                "userPw",
                ifNullToNullString(userPw),
                "userAddress",
                ifNullToNullString(userAddress),
                "userEmail",
                ifNullToNullString(userEmail),
                "userHp",
                ifNullToNullString(userHp))));
  }
}
