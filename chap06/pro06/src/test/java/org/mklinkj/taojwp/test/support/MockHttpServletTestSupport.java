package org.mklinkj.taojwp.test.support;

import jakarta.servlet.http.HttpServlet;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/** Mock HTTP Servlet 테스트를 위한 공통 테스트 지원 클래스 */
public abstract class MockHttpServletTestSupport<T extends HttpServlet> {

  protected T servlet;
  protected MockHttpServletRequest request;

  protected MockHttpServletResponse response;

  public MockHttpServletTestSupport() {
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();

    request.setContextPath("/pro06");
  }

  protected void setServlet(T servlet) {
    this.servlet = servlet;
  }
}
