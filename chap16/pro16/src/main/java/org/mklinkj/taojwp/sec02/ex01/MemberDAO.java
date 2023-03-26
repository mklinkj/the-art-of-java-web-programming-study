package org.mklinkj.taojwp.sec02.ex01;

import static org.mklinkj.taojwp.common.util.SqlSessionFactoryHelper.sqlSessionFactory;

import java.sql.Connection;
import org.apache.ibatis.session.SqlSession;
import org.mklinkj.taojwp.mapper.MemberMapper;

/** MyBatis 추가해보자! */
public class MemberDAO {
  /*
   * SqlSessionFactory 에서 SqlSession 만들기
   * <a href="https://mybatis.org/mybatis-3/ko/getting-started.html#sqlsessionfactory-%EC%97%90%EC%84%9C-sqlsession-%EB%A7%8C%EB%93%A4%EA%B8%B0">...</a>
   */
  public boolean overlappedId(String id) {
    try (SqlSession sqlSession = sqlSessionFactory().openSession()) {
      MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
      return memberMapper.overlappedId(id);
    }
  }

  /** 바깥에서 정의한 트랜젝션 커낵션을 사용하도록 Connection을 인자로 전달함. */
  public int insertMember(MemberVO memberVO, Connection connection) {
    SqlSession sqlSession = sqlSessionFactory().openSession(connection);
    MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
    return memberMapper.insertMember(memberVO);
  }
}
