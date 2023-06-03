package org.mklinkj.taojwp.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.util.ControllerUtil.getViewName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.mock.web.MockHttpServletRequest;

@RunWith(JUnit4.class)
public class ControllerUtilTests {

  private MockHttpServletRequest request;

  @Before
  public void before() {
    request = new MockHttpServletRequest();
  }

  @Test
  public void testGetViewName_01() {
    request.setRequestURI("/member/listMembers.do");

    String viewName = getViewName(request);
    assertThat(viewName).isEqualTo("listMembers");
  }

  @Test
  public void testGetViewName_02() {
    request.setRequestURI("/member/memberDetail.do?id=mklinkj");

    String viewName = getViewName(request);
    assertThat(viewName).isEqualTo("memberDetail");
  }

  /*
   * /로 끝나는 경우 어떤 뷰를 선택해야할지? 지금 코드로는 뷰네임이 ""가 되기는 한다.
   * 이 경우는 어떻게 판단해야할지 모르겠다. 예외를 던져야하나?
   */
  @Test
  public void testGetViewName_03() {
    request.setRequestURI("/member/listMembers/");
    String viewName = getViewName(request);
    assertThat(viewName).isEqualTo("");
  }
}
