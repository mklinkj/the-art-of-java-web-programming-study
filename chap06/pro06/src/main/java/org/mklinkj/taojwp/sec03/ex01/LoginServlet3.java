package org.mklinkj.taojwp.sec03.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/login3")
public class LoginServlet3 extends HttpServlet {
  private static final String UTF_8_ENCODING = StandardCharsets.UTF_8.name();

  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(String.format("text/html;charset=%s", UTF_8_ENCODING));

    String userId = request.getParameter("user_id");
    String password = request.getParameter("user_pw");

    LOGGER.info("아이디: {}", userId);
    LOGGER.info("비밀번호: {}", password);
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 메서드 호출");
  }
}
