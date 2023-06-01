package org.mklinkj.taojwp.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(JUnit4.class)
public class ViewNameTest {
  private final UserController userController = new UserController();

  private MockHttpServletRequest request;

  @Before
  public void before() {
    request = new MockHttpServletRequest();
  }

  @Test
  public void testGetViewName_01() {
    request.setRequestURI("/pro21-spring4-ex02/test/login.do");
    String viewName = ReflectionTestUtils.invokeMethod(userController, "getViewName", request);
    assertThat(viewName).isEqualTo("login");
  }

  @Test
  public void testGetViewName_02() {
    request.setRequestURI("/pro21-spring4-ex02/test/login");
    String viewName = ReflectionTestUtils.invokeMethod(userController, "getViewName", request);
    assertThat(viewName).isEqualTo("login");
  }

  @Test
  public void testGetViewName_03() {
    request.setRequestURI("/pro21-spring4-ex02/test/memberInfo.do;abc");
    String viewName = ReflectionTestUtils.invokeMethod(userController, "getViewName", request);
    assertThat(viewName).isEqualTo("memberInfo");
  }

  @Test
  public void testGetViewName_04() {
    request.setRequestURI("/pro21-spring4-ex02/test/memberInfo.do?name=1&value=2");
    String viewName = ReflectionTestUtils.invokeMethod(userController, "getViewName", request);
    assertThat(viewName).isEqualTo("memberInfo");
  }

  /*
   * /로 끝나는 경우 어떤 뷰를 선택해야할지? 지금 코드로는 뷰네임이 ""가 되기는 한다.
   * 이 경우는 어떻게 판단해야할지 모르겠다. 예외를 던져야하나?
   */
  @Test
  public void testGetViewName_05() {
    request.setRequestURI("/pro21-spring4-ex02/test/memberInfo/");
    String viewName = ReflectionTestUtils.invokeMethod(userController, "getViewName", request);
    assertThat(viewName).isEqualTo("");
  }
}
