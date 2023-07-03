package org.mklinkj.taojwp.board.dao;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.board.domain.ArticleVO;
import org.mklinkj.taojwp.board.mapper.OracleBoardMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {

  private final OracleBoardMapper oracleBoardMapper;

  @Override
  public List<ArticleVO> selectAllArticles() {
    return oracleBoardMapper.selectAllArticles();
  }

  @Override
  public int insertNewArticle(ArticleVO article) {

    int newArticleNo = oracleBoardMapper.selectMaxArticleNo() + 1;
    article.setArticleNo(newArticleNo);
    oracleBoardMapper.insertArticle(article);
    return newArticleNo;
  }

  @Override
  public ArticleVO selectArticle(int articleNo) {
    return oracleBoardMapper.selectOne(articleNo);
  }

  @Override
  public int updateArticle(ArticleVO articleVO) {
    return oracleBoardMapper.updateArticle(articleVO);
  }

  @Override
  public int deleteArticle(int articleNo) {
    return oracleBoardMapper.deleteArticle(articleNo);
  }

  @Override
  public List<Integer> selectArticleNumbersToDelete(int articleNo) {
    return oracleBoardMapper.selectArticleNumbersToDelete(articleNo);
  }

  @Override
  public List<ArticleVO> selectPagedArticles(Map<String, Integer> pagingMap) {
    return oracleBoardMapper.selectPagedArticles(pagingMap.get("section"), pagingMap.get("pageNum"));
  }

  @Override
  public int selectCountTotalArticles() {
    return oracleBoardMapper.selectCountTotalArticles();
  }
}
