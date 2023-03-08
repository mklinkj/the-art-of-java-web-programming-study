package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sess")
public class SessionTest extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession();
    session.setAttribute("name", "이순신");

    out.printf("""
    <!DOCTYPE html>
    <html lang="ko">
    <head>
      <meta charset="UTF-8">
      <title>session 내장 객체에 데이터 바인딩 테스트</title>
    </head>
    <body>
      <h1>세션에 이름을 바인딩합니다.</h1>
      <a href='/pro12/test01/session1.jsp'>첫 번째 페이지로 이동하기</a>
    </body>
    </html>
    """);

  }
}
