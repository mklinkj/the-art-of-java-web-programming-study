package org.mklinkj.taojwp.sec03.brd01;

import static org.mklinkj.taojwp.common.util.SqlSessionFactoryHelper.sqlSessionFactory;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.mklinkj.taojwp.mapper.BoardMapper;

public class BoardDAO {

  public List<ArticleVO> selectAllArticles() {
    try (SqlSession sqlSession = sqlSessionFactory().openSession()) {
      BoardMapper memberMapper = sqlSession.getMapper(BoardMapper.class);
      return memberMapper.selectAllArticles();
    }
  }
}
