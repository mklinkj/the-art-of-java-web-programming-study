package org.mklinkj.taojwp.sec01.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 이 요청의 본문에 사용된 문자 인코딩의 이름을 재정의 함.
    // 유입된 본문이 UTF-8 형식 데이터라고 간주함.
    // 입력을 읽기전에 설정해야 효과가 있음.
    request.setCharacterEncoding(StandardCharsets.UTF_8.name());

    String userId = request.getParameter("user_id");
    String password = request.getParameter("user_pw");

    LOGGER.info("아이디: {}", userId);
    LOGGER.info("암호: {}", password);
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 메서드 호출");
  }
}
