package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.util.SqlSessionFactoryHelper.sqlSessionFactory;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import org.apache.ibatis.session.SqlSession;
import org.mklinkj.taojwp.mapper.MemberMapper;

/** MyBatis 추가해보자! */
public class MemberDAO {

  public List<MemberVO> listMembers() {
    try (SqlSession sqlSession = sqlSessionFactory().openSession()) {
      MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
      return memberMapper.listMembers();
    }
  }

  public int addMember(MemberVO memberVO) {
    try (SqlSession sqlSession = sqlSessionFactory().openSession(true)) {
      MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
      return memberMapper.addMember(memberVO);
    }
  }
}
