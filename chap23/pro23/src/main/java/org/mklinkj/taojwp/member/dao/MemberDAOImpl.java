package org.mklinkj.taojwp.member.dao;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
  private final SqlSessionFactory sqlSessionFactory;

  public MemberDAOImpl() {
    try {
      EncodedResource resource =
          new EncodedResource(new ClassPathResource("mybatis-config.xml"), StandardCharsets.UTF_8);
      this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource.getInputStream());

    } catch (Exception e) {
      throw new IllegalStateException("SqlSessionFactory 생성 실패", e);
    }
  }

  @Override
  public List<MemberVO> selectAllMembers(SearchDTO searchDTO) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.getMapper(MemberDAO.class).selectAllMembers(searchDTO);
    }
  }

  @Override
  public int addMember(MemberVO memberVO) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int updateRowCount = sqlSession.getMapper(MemberDAO.class).addMember(memberVO);
      sqlSession.commit();
      return updateRowCount;
    }
  }

  @Override
  public MemberVO findById(String id) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.getMapper(MemberDAO.class).findById(id);
    }
  }

  @Override
  public int updateMember(MemberVO memberVO) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int updateRowCount = sqlSession.getMapper(MemberDAO.class).updateMember(memberVO);
      sqlSession.commit();
      return updateRowCount;
    }
  }

  @Override
  public int deleteMember(String id) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int updateRowCount = sqlSession.getMapper(MemberDAO.class).deleteMember(id);
      sqlSession.commit();
      return updateRowCount;
    }
  }

  @Override
  public List<MemberVO> searchMember(MemberVO memberVO) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.getMapper(MemberDAO.class).searchMember(memberVO);
    }
  }

  @Override
  public List<MemberVO> foreachSelect(List<String> nameList) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.getMapper(MemberDAO.class).foreachSelect(nameList);
    }
  }

  @Override
  public int foreachInsert(List<MemberVO> memberList) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int updateRowCount = sqlSession.getMapper(MemberDAO.class).foreachInsert(memberList);
      sqlSession.commit();
      return updateRowCount;
    }
  }
}
