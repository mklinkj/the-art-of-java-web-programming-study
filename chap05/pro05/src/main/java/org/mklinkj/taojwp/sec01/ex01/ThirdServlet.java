package org.mklinkj.taojwp.sec01.ex01;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/third")
public class ThirdServlet extends HttpServlet {
  @Override
  public void init() {
    LOGGER.info("{}} init 메서드 호출 >>> ", getClass().getSimpleName());
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    LOGGER.info("{} doGet 메서드 호출 >>> ", getClass().getSimpleName());
    try (PrintWriter pw = response.getWriter()) {
      pw.print(getClass().getSimpleName());
    }
  }

  @Override
  public void destroy() {
    LOGGER.info("{} destroy 메서드 호출 >>>", getClass().getSimpleName());
  }
}
