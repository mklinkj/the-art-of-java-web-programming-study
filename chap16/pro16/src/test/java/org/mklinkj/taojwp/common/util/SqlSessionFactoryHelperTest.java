package org.mklinkj.taojwp.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.util.SqlSessionFactoryHelper.sqlSessionFactory;

import java.time.LocalDateTime;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.mapper.MemberMapper;
import org.mklinkj.taojwp.sec02.ex01.MemberVO;

class SqlSessionFactoryHelperTest {
  @DisplayName("SessionFactory는 싱글톤으로 관리되야한다.")
  @Test
  void testSessionFactory_isSame() {
    SqlSessionFactory a = sqlSessionFactory();
    SqlSessionFactory b = sqlSessionFactory();
    assertThat(a).isSameAs(b);
  }

  @Test
  void testOverlappedId() {
    SqlSessionFactory factory = sqlSessionFactory();
    try (SqlSession sqlSession = factory.openSession()) {
      MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
      assertThat(memberMapper.overlappedId("mklinkj")).isTrue();
    }
  }

  @Test
  void testInsertMember() {
    // openSession의 기본 값이 autoCommit : false 이다.
    // LocalDateTime 변환도 잘 됨.
    try (SqlSession sqlSession = sqlSessionFactory().openSession(false)) {
      MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
      MemberVO vo = new MemberVO();
      vo.setId("test00");
      vo.setPwd("1234");
      vo.setName("테스트유저");
      vo.setEmail("test@test.com");
      vo.setJoinDate(LocalDateTime.of(2023, 3, 5, 10, 30));
      memberMapper.insertMember(vo);
    }
  }
}
