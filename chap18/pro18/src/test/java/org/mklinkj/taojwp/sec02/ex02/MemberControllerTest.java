package org.mklinkj.taojwp.sec02.ex02;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class MemberControllerTest extends MockHttpServletTestSupport<MemberController> {

  @Autowired private DBDataInitializer dbDataInitializer;

  @Test
  void testDoHandle_list() throws Exception {
    runGivenWhenThen(
        () -> servlet.init(), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl()).isEqualTo("/test03/listMembers.jsp");
        });
  }

  @Test
  void testDoHandle_addMember() throws Exception {
    dbDataInitializer.resetDB();
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

          HttpSession session = request.getSession();
          assertThat(session.getAttribute("msg")).isEqualTo("registered");

          assertThat(response.getRedirectedUrl()).isEqualTo("/member2/listMembers.do");
        });
  }

  @Test
  void testDoHandle_modMember() throws Exception {
    dbDataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setPathInfo("/modMember.do");
          request.setParameter("id", "mklinkj");
          request.setParameter("name", "정션링크_이름수정");
          request.setParameter("pwd", "4321");
          request.setParameter("email", "mklinkj_mod@test.com");
        }, //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());

          HttpSession session = request.getSession();
          assertThat(session.getAttribute("msg")).isEqualTo("modified");

          assertThat(response.getRedirectedUrl()).isEqualTo("/member2/listMembers.do");
        });
  }

  @Test
  void testDoHandle_delMember() throws Exception {
    dbDataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setPathInfo("/delMember.do");
          request.setParameter("id", "mklinkj");
        }, //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());

          HttpSession session = request.getSession();
          assertThat(session.getAttribute("msg")).isEqualTo("deleted");

          assertThat(response.getRedirectedUrl()).isEqualTo("/member2/listMembers.do");
        });
  }

  @Override
  protected Class<MemberController> getServletClass() {
    return MemberController.class;
  }

  @Override
  protected String getServletPath() {
    return "/member2";
  }
}
