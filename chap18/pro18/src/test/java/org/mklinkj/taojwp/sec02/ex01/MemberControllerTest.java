package org.mklinkj.taojwp.sec02.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class MemberControllerTest extends MockHttpServletTestSupport<MemberController> {

  @Autowired private DBDataInitializer dataInitializer;

  @Test
  void testDoHandle_list() throws Exception {
    runGivenWhenThen(
        () -> servlet.init(), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()).isEqualTo("/test02/listMembers.jsp");
        });
  }

  @Test
  void testDoHandle_addMember() throws Exception {
    dataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setPathInfo("/addMember.do");
          request.setParameter("id", "user001");
          request.setParameter("name", "사용자001");
          request.setParameter("pwd", "1234");
          request.setParameter("email", "user001@test.com");
        }, //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()).isEqualTo("/member1/listMembers.do");
        });
  }

  @Override
  protected Class<MemberController> getServletClass() {
    return MemberController.class;
  }

  @Override
  protected String getServletPath() {
    // 구동해서 로그 찍어봄..
    // /member/* 게 설정되어있을 때.. 뒤에 무슨 값이 나오든.. 앞의 경로까지만 들어온다.
    return "/member1";
  }
}
