package org.mklinkj.taojwp.test.support;

import static org.mklinkj.taojwp.test.support.TestConstants.CONTEXT_PATH;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import org.mockito.Mockito;
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
    request.setContextPath(getContextPath());
    request.setServletPath(getServletPath());
    response = new MockHttpServletResponse();
    servlet = mockServlet();
  }

  private MockServletConfig createMockServletConfig(ServletContext context) {
    return new MockServletConfig(context);
  }

  private MockServletContext createMockServletContext() {
    return new MockServletContext();
  }

  private T mockServlet() {
    Class<T> servletClass = getServletClass();
    T mockServlet = Mockito.mock(servletClass, Mockito.CALLS_REAL_METHODS);
    Mockito.when(mockServlet.getServletConfig()).thenReturn(servletConfig);
    Mockito.when(mockServlet.getServletContext()).thenReturn(servletContext);
    return mockServlet;
  }

  protected abstract Class<T> getServletClass();

  protected String getContextPath() {
    return CONTEXT_PATH;
  }

  protected abstract String getServletPath();
}
