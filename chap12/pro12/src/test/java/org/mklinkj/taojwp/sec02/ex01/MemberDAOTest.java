package org.mklinkj.taojwp.sec02.ex01;

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
  void testListMembersWithMemberName() {
    MemberVO vo = new MemberVO();
    vo.setName("정션링크");
    List<MemberVO> result = dao.listMembers(vo);

    assertThat(result).hasSize(1);
    assertThat(result.get(0)) //
        .hasFieldOrPropertyWithValue("name", "정션링크");
  }

  @Test
  void testListMembersAll() {
    MemberVO vo = new MemberVO();
    List<MemberVO> result = dao.listMembers(vo);
    assertThat(result).isNotEmpty();
  }
}