package org.mklinkj.taojwp.common.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractHttpServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doHandle(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doHandle(request, response);
  }

  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {}

  private static final String FLASH_KEY_PREFIX = "flash___";

  protected void setFlashAttribute(HttpServletRequest request, String key, Object value) {
    HttpSession session = request.getSession();
    session.setAttribute(FLASH_KEY_PREFIX + key, 1);
    session.setAttribute(key, value);
  }

  protected void cleanFlashAttribute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    Enumeration<String> names = request.getSession().getAttributeNames();

    while (names.hasMoreElements()) {
      String KeyName = names.nextElement();
      if (KeyName.startsWith(FLASH_KEY_PREFIX)) {
        int count = (int) session.getAttribute(KeyName);
        if (count == 1) {
          session.setAttribute(KeyName, 0);
        } else {
          session.removeAttribute(KeyName);
          session.removeAttribute(KeyName.replace(FLASH_KEY_PREFIX, ""));
        }
      }
    }
  }

  protected void forwardOrRedirect(
      HttpServletRequest request, HttpServletResponse response, String nextPage)
      throws IOException, ServletException {
    if (nextPage == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } else if (nextPage.startsWith("redirect:")) {
      String redirectUrl = getServletContext().getContextPath() + nextPage.replace("redirect:", "");
      LOGGER.info("redirectUrl: {}", redirectUrl);
      response.sendRedirect(redirectUrl);
    } else {
      LOGGER.info("forwardUrl: {}", nextPage);
      RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
      dispatch.forward(request, response);
    }
  }
}
