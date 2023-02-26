package org.mklinkj.taojwp.sec01.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class MemberDAOTest {

  private MemberDAO dao;

  @BeforeEach
  void beforeEach() {
    dao = new MemberDAO();
  }

  @Test
  void listMembers() {
    List<MemberVO> list = dao.listMembers();
    assertThat(list).isNotNull();
    list.forEach(member -> LOGGER.info("{}", member));
  }
}
