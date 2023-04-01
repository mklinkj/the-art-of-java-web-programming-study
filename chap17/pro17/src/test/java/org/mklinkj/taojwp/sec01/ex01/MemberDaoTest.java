package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBUtils;

class MemberDaoTest {
  private MemberDAO dao;

  @BeforeEach
  void beforeEach() {
    DBUtils.resetDB();
    dao = new MemberDAO();
  }

  @Test
  void testListMembers() {
    List<MemberVO> list = dao.listMembers();
    assertThat(list).isNotEmpty();
  }

  @Test
  void testAddMember() {
    MemberVO member =
        MemberVO.builder() //
            .id("user00")
            .name("사용자00")
            .pwd("1234")
            .email("user00@test.com")
            .build();

    assertThat(dao.addMember(member)).isEqualTo(1);
  }
}
