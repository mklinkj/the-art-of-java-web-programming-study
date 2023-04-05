package org.mklinkj.taojwp.sec03.brd01;

import static org.mklinkj.taojwp.common.util.DBUtils.executeMapper;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import org.apache.ibatis.session.SqlSession;
import org.mklinkj.taojwp.mapper.BoardMapper;

public class BoardDAO {

  /** 기본 상태를 항상 AutoCommit True로 설정 */
  private <T> T execute(BiFunction<SqlSession, BoardMapper, T> function) {
    return executeMapper(function, BoardMapper.class, true);
  }

  public List<ArticleVO> selectAllArticles() {
    return execute((sqlSession, boardMapper) -> boardMapper.selectAllArticles());
  }

  public int insertNewArticle(ArticleVO article) {
    return execute(
        (sqlSession, boardMapper) -> {
          int newArticleNo = boardMapper.selectMaxArticleNo() + 1;
          article.setArticleNo(newArticleNo);
          boardMapper.insertArticle(article);
          return newArticleNo;
        });
  }

  public ArticleVO selectArticle(int articleNo) {
    return execute((sqlSession, boardMapper) -> boardMapper.selectOne(articleNo));
  }

  public int updateArticle(ArticleVO articleVO) {
    return execute((sqlSession, boardMapper) -> boardMapper.updateArticle(articleVO));
  }

  public int deleteArticle(int articleNo) {
    return execute((sqlSession, boardMapper) -> boardMapper.deleteArticle(articleNo));
  }

  public List<Integer> selectArticleNumbersToDelete(int articleNo) {
    return execute(
        (sqlSession, boardMapper) -> boardMapper.selectArticleNumbersToDelete(articleNo));
  }

  public List<ArticleVO> selectPagedArticles(Map<String, Integer> pagingMap) {
    return execute(
        (sqlSession, boardMapper) ->
            boardMapper.selectPagedArticles(pagingMap.get("section"), pagingMap.get("pageNum")));
  }

  public int selectCountTotalArticles() {
    return execute((sqlSession, boardMapper) -> boardMapper.selectCountTotalArticles());
  }
}
