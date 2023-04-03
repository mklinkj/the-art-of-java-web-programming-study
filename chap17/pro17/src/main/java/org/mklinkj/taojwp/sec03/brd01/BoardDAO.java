package org.mklinkj.taojwp.sec03.brd01;

import static org.mklinkj.taojwp.common.util.SqlSessionFactoryHelper.sqlSessionFactory;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.mklinkj.taojwp.mapper.BoardMapper;

public class BoardDAO {

  public List<ArticleVO> selectAllArticles() {
    try (SqlSession sqlSession = sqlSessionFactory().openSession()) {
      BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
      return boardMapper.selectAllArticles();
    }
  }

  public int insertNewArticle(ArticleVO article) {
    try (SqlSession sqlSession = sqlSessionFactory().openSession(true)) {

      BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
      int newArticleNo = boardMapper.selectMaxArticleNo() + 1;
      article.setArticleNo(newArticleNo);

      boardMapper.insertArticle(article);

      return newArticleNo;
    }
  }
}
