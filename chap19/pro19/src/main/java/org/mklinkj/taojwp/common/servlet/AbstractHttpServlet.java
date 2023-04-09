package org.mklinkj.taojwp.common.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Slf4j
public abstract class AbstractHttpServlet extends HttpServlet {
  protected ApplicationContext applicationContext;

  @Override
  public void init() {
    applicationContext =
        WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
  }

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

  protected void setFlashAttribute(HttpServletRequest request, String key, Object value) {
    FlashAttributeUtil.setFlashAttribute(request, key, value);
  }

  /** 필터로 등록하는 것이 나을지 고민을 해봐야겠다. */
  protected void cleanFlashAttribute(HttpServletRequest request) {
    FlashAttributeUtil.cleanFlashAttribute(request);
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
