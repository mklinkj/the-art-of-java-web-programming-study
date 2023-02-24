package org.mklinkj.taojwp.sec01.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/input")
public class InputServlet extends HttpServlet {
  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(StandardCharsets.UTF_8.name());

    String userId = request.getParameter("user_id");
    String password = request.getParameter("user_pw");

    LOGGER.info("아이디: {}", userId);
    LOGGER.info("암호: {}", password);

    String[] subjects = request.getParameterValues("subject");

    Arrays.stream(subjects).forEach(s -> LOGGER.info("선택한 과목: {}", s));
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 메서드 호출");
  }
}
