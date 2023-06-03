package org.mklinkj.taojwp.member.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:config/action-dataSource.xml")
class MemberDAOImplTests {

  @Autowired private MemberDAO memberDAO;

  @Test
  void testSelectAllMembers() {
    List<MemberVO> list = memberDAO.selectAllMembers();
    assertThat(list).isNotEmpty();
  }

  @Test
  void testAddMembers() {
    MemberVO member = new MemberVO();
    member.setId("jdbc");
    member.setName("제이");
    member.setPwd("1234");
    member.setEmail("jdbc@jdbc.org");

    int result = memberDAO.addMember(member);

    assertThat(result).isEqualTo(1);
  }

  @Test
  void testFindById() {
    MemberVO member = memberDAO.findById("mklinkj");
    assertThat(member) //
        .isNotNull()
        .hasFieldOrPropertyWithValue("id", "mklinkj");
  }
}
