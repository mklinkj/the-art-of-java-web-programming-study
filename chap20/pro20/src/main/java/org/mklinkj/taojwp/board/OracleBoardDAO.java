package org.mklinkj.taojwp.board;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.mapper.BoardMapper;

@RequiredArgsConstructor
public class OracleBoardDAO implements BoardDAO {

  private final BoardMapper boardMapper;

  public List<ArticleVO> selectAllArticles() {
    return boardMapper.selectAllArticles();
  }

  public int insertNewArticle(ArticleVO article) {

    int newArticleNo = boardMapper.selectMaxArticleNo() + 1;
    article.setArticleNo(newArticleNo);
    boardMapper.insertArticle(article);
    return newArticleNo;
  }

  public ArticleVO selectArticle(int articleNo) {
    return boardMapper.selectOne(articleNo);
  }

  public int updateArticle(ArticleVO articleVO) {
    return boardMapper.updateArticle(articleVO);
  }

  public int deleteArticle(int articleNo) {
    return boardMapper.deleteArticle(articleNo);
  }

  public List<Integer> selectArticleNumbersToDelete(int articleNo) {
    return boardMapper.selectArticleNumbersToDelete(articleNo);
  }

  public List<ArticleVO> selectPagedArticles(Map<String, Integer> pagingMap) {
    return boardMapper.selectPagedArticles(pagingMap.get("section"), pagingMap.get("pageNum"));
  }

  public int selectCountTotalArticles() {
    return boardMapper.selectCountTotalArticles();
  }
}
