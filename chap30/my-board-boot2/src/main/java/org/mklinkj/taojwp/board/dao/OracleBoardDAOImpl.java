package org.mklinkj.taojwp.board.dao;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.board.domain.ArticleVO;
import org.mklinkj.taojwp.board.mapper.BoardMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OracleBoardDAOImpl implements BoardDAO {

  private final BoardMapper boardMapper;

  @Override
  public List<ArticleVO> selectAllArticles() {
    return boardMapper.selectAllArticles();
  }

  @Override
  public int insertNewArticle(ArticleVO article) {

    int newArticleNo = boardMapper.selectMaxArticleNo() + 1;
    article.setArticleNo(newArticleNo);
    boardMapper.insertArticle(article);
    return newArticleNo;
  }

  @Override
  public ArticleVO selectArticle(int articleNo) {
    return boardMapper.selectOne(articleNo);
  }

  @Override
  public int updateArticle(ArticleVO articleVO) {
    return boardMapper.updateArticle(articleVO);
  }

  @Override
  public int deleteArticle(int articleNo) {
    return boardMapper.deleteArticle(articleNo);
  }

  @Override
  public List<Integer> selectArticleNumbersToDelete(int articleNo) {
    return boardMapper.selectArticleNumbersToDelete(articleNo);
  }

  @Override
  public List<ArticleVO> selectPagedArticles(Map<String, Integer> pagingMap) {
    return boardMapper.selectPagedArticles(pagingMap.get("section"), pagingMap.get("pageNum"));
  }

  @Override
  public int selectCountTotalArticles() {
    return boardMapper.selectCountTotalArticles();
  }
}
