package org.mklinkj.taojwp.sec01.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/input2")
public class InputServlet2 extends HttpServlet {
  @Override
  public void init() {
    LOGGER.info("init 메서드 호출");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(StandardCharsets.UTF_8.name());

    Enumeration<String> enu = request.getParameterNames();
    while(enu.hasMoreElements()) {
      String name = enu.nextElement();
      String[] values = request.getParameterValues(name);
      for(String value: values) {
        LOGGER.info("name={}, value={}", name, value);
      }
    }
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 메서드 호출");
  }
}
