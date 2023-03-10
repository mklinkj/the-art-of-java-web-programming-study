package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class MemberDAOTest {

  private MemberDAO dao;

  @BeforeEach
  void beforeEach() {
    this.dao = new MemberDAO();
  }

  @Test
  void listMembers() {
    List<MemberVO> list = dao.listMembers();
    assertThat(list).isNotNull();
    list.forEach(member -> LOGGER.info("{}", member));
  }

  @Test
  void addMemberThenDelMember() {
    MemberVO vo = new MemberVO();
    vo.setId("mklinkj");
    vo.setName("mee");
    vo.setPwd("1234");
    vo.setEmail("123@123.com");
    vo.setJoinDate(LocalDateTime.now());
    dao.addMember(vo);

    assertThat(dao.delMember("mklinkj")).isEqualTo(1);
  }
}
