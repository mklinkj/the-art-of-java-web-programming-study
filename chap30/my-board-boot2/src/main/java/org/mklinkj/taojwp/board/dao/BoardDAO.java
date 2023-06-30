package org.mklinkj.taojwp.board.dao;

import java.util.List;
import java.util.Map;
import org.mklinkj.taojwp.board.domain.ArticleVO;

public interface BoardDAO {

  List<ArticleVO> selectAllArticles();

  int insertNewArticle(ArticleVO article);

  ArticleVO selectArticle(int articleNo);

  int updateArticle(ArticleVO articleVO);

  int deleteArticle(int articleNo);

  List<Integer> selectArticleNumbersToDelete(int articleNo);

  List<ArticleVO> selectPagedArticles(Map<String, Integer> pagingMap);

  int selectCountTotalArticles();
}
