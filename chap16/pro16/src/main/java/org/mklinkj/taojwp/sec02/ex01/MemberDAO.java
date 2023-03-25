package org.mklinkj.taojwp.sec02.ex01;

import static org.mklinkj.taojwp.common.util.SqlSessionFactoryHelper.sqlSessionFactory;
import static org.mklinkj.taojwp.common.util.TransactionFactoryHelper.transactionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mklinkj.taojwp.common.util.DBUtils;
import org.mklinkj.taojwp.mapper.MemberMapper;

/** MyBatis 추가해보자! */
public class MemberDAO {
  /**
   * SqlSessionFactory 에서 SqlSession 만들기
   * https://mybatis.org/mybatis-3/ko/getting-started.html#sqlsessionfactory-%EC%97%90%EC%84%9C-sqlsession-%EB%A7%8C%EB%93%A4%EA%B8%B0
   */
  public boolean overlappedId(String id) {
    try (SqlSession sqlSession = sqlSessionFactory().openSession()) {
      MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
      return memberMapper.overlappedId(id);
    }
  }

  public int insertMember(MemberVO memberVO, boolean autoCommit) {
    try (SqlSession sqlSession = sqlSessionFactory().openSession(autoCommit)) {
      MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
      return memberMapper.insertMember(memberVO);
    }
  }
}
