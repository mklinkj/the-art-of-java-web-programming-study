package org.mklinkj.taojwp.sec02.ex01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.util.TransactionFactoryHelper.newTransaction;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBUtils;

@Slf4j
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

  /** */
  @Test
  void testInsertMember() throws Exception {
    final Transaction tx = newTransaction();

    try {
      MemberVO vo0 = new MemberVO();
      vo0.setId("test00");
      vo0.setPwd("1234");
      vo0.setName("테스트유저00");
      vo0.setEmail("test00@test.com");
      vo0.setJoinDate(LocalDateTime.of(2023, 12, 25, 0, 0));
      dao.insertMember(vo0, tx.getConnection()); // autoCommit: false

      MemberVO vo1 = new MemberVO();
      vo1.setId("test01");
      vo1.setPwd("1234");
      vo1.setName("테스트유저01");
      vo1.setEmail("test01@test.com");
      vo1.setJoinDate(LocalDateTime.of(2023, 12, 25, 1, 0));
      dao.insertMember(vo1, tx.getConnection()); // autoCommit: false

      LOGGER.info("### tx.commit() 실행 직전... ###");
      tx.commit();
      LOGGER.info("### tx.commit() 실행 후... ###");
    } finally {
      tx.close(); // 트랜젝션을 close()하면 Connection을 close하고 autoCommit 상태를 true로 바꾼다.
    }

    assertThat(dao.overlappedId("test00")).isTrue();
    assertThat(dao.overlappedId("test01")).isTrue();
    assertThat(dao.overlappedId("noUser00")).isFalse();
    assertThat(dao.overlappedId("noUser01")).isFalse();
  }
}
