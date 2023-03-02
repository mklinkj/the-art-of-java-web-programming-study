package org.mklinkj.taojwp.sec05.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/daoSessionLogin")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    doHandle(request, response);
  }

  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(String.format(HTML_CONTENT_TYPE));
    PrintWriter out = response.getWriter();

    String userId = request.getParameter("user_id");
    String userPwd = request.getParameter("user_pwd");

    MemberVO memberVO = new MemberVO();
    memberVO.setId(userId);
    memberVO.setPwd(userPwd);

    MemberDAO dao = new MemberDAO();
    boolean result = dao.isExisted(memberVO);

    if (result) {
      HttpSession session = request.getSession();
      session.setAttribute("isLogon", true);
      session.setAttribute("login.id", userId);
      session.setAttribute("login.pwd", userPwd);

      out.printf(
          """
          <!DOCTYPE html>
          <html lang="ko">
          <head>
            <meta charset="UTF-8">
            <title>로그인 성공</title>
          </head>
          <body>
            <h4>안녕하세요 %s님</h4>
            <a href="show">회원정보 보기</a>
          </body>
          <html>
          """,
          userId);
    } else {
      out.print(
          """
          <!DOCTYPE html>
          <html lang="ko">
          <head>
            <meta charset="UTF-8">
            <title>로그인 실패</title>
          </head>
          <body>
            <h4>회원 아이디가 틀립니다.</h4>
            <a href="login4.html">다시 로그인하기</a>
          </body>
          <html>
          """);
    }
  }
}
