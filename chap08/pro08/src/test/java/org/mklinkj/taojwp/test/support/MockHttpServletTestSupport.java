package org.mklinkj.taojwp.test.support;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

/** Mock HTTP Servlet 테스트를 위한 공통 테스트 지원 클래스 */
public abstract class MockHttpServletTestSupport<T extends HttpServlet> {

  protected T servlet;
  protected MockHttpServletRequest request;

  protected MockHttpServletResponse response;
  protected MockServletContext servletContext;

  protected MockServletConfig servletConfig;

  public MockHttpServletTestSupport() {
    resetMock();
  }

  protected void resetMock() {
    servletContext = createMockServletContext();
    servletConfig = createMockServletConfig(servletContext);
    request = new MockHttpServletRequest(servletContext);
    response = new MockHttpServletResponse();
    request.setContextPath("/pro08");
  }

  private MockServletConfig createMockServletConfig(ServletContext context) {
    return new MockServletConfig(context);
  }

  private MockServletContext createMockServletContext() {
    return new MockServletContext();
  }

  protected void setServlet(T servlet) {
    this.servlet = servlet;
  }
}
