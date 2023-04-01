package org.mklinkj.taojwp.test.support;

import static org.mklinkj.taojwp.test.support.TestConstants.CONTEXT_PATH;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import org.mklinkj.taojwp.common.util.DBUtils;
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
    return new MockServletContext(getContextPath());
  }

  private T mockServlet() {
    Class<T> servletClass = getServletClass();
    T mockServlet = Mockito.spy(servletClass);
    Mockito.when(mockServlet.getServletConfig()).thenReturn(servletConfig);
    Mockito.when(mockServlet.getServletContext()).thenReturn(servletContext);
    return mockServlet;
  }

  protected abstract Class<T> getServletClass();

  protected String getContextPath() {
    return CONTEXT_PATH;
  }

  protected abstract String getServletPath();

  /**
   * Given - When - Then 패턴 모양으로 실행을 도와주는 메서드
   *
   * @param given 테스트 준비 코드
   * @param when 테스트 실행
   * @param then 테스트 검증
   * @throws Exception 테스트 코드 실행 중, 어떤 예외든 발생할 수 있으므로 Exception으로 정의
   */
  protected void runGivenWhenThen(
      ExceptionableRunnable given, ExceptionableRunnable when, ExceptionableRunnable then)
      throws Exception {
    resetMock();
    given.run();
    when.run();
    then.run();
  }

  /** DB 데이터 초기화 필요시 호출 */
  protected void resetDB() {
    DBUtils.resetDB();
  }
}
