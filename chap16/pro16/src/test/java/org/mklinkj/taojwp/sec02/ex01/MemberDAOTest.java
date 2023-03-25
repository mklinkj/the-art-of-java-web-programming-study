package org.mklinkj.taojwp.sec02.ex01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBUtils;

class MemberDAOTest {

  private MemberDAO dao;

  @BeforeEach
  void beforeEach() {
    DBUtils.resetDB();
    dao = new MemberDAO();
  }

  @Test
  void testOverlappedId() {
    assertThat(dao.overlappedId("mklinkj")).isTrue();
    assertThat(dao.overlappedId("mklinkj2")).isFalse();
  }
}
