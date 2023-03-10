package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.DBUtils.resetDB;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberDAOTest {

  private MemberDAO dao;

  @BeforeEach
  void beforeEach() {
    resetDB();
    dao = new MemberDAO();
  }

  @Test
  void testListMembers() {
    List<MemberBean> list = dao.listMembers();
    assertThat(list).isNotEmpty();
  }

  @Test
  void testListMembers_passUser() {
    MemberBean member = new MemberBean();
    member.setName("정션링크");
    List<MemberBean> list = dao.listMembers(member);
    assertThat(list).hasSize(1);
  }

  @Test
  void testAddMember() {
    MemberBean member = new MemberBean();
    member.setId("new_id");
    member.setName("new_name");
    member.setPwd("1111");
    member.setEmail("new@email.com");

    dao.addMember(member);
  }

  @Test
  void testDelMember() {
    assertThat(dao.delMember("mklinkj")).isEqualTo(1);
  }

  @Test
  void testIsExisted() {
    MemberBean member = new MemberBean();
    member.setId("mklinkj");
    member.setPwd("1234");
    assertThat(dao.isExisted(member)).isEqualTo(true);
  }
}
