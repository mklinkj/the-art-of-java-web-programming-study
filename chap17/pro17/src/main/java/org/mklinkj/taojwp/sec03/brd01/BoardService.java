package org.mklinkj.taojwp.sec03.brd01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardService {
  private final BoardDAO boardDAO;

  public BoardService() {
    boardDAO = new BoardDAO();
  }

  public List<ArticleVO> listArticles() {
    return boardDAO.selectAllArticles();
  }

  public int addArticle(ArticleVO article) {
    return boardDAO.insertNewArticle(article);
  }

  public ArticleVO viewArticle(int articleNo) {
    return boardDAO.selectArticle(articleNo);
  }

  public int modArticle(ArticleVO article) {
    return boardDAO.updateArticle(article);
  }

  public List<Integer> removeArticle(int articleNo) {
    List<Integer> removedArticleNo = boardDAO.selectArticleNumbersToDelete(articleNo);
    boardDAO.deleteArticle(articleNo);
    return removedArticleNo;
  }

  public int addReply(ArticleVO article) {
    return boardDAO.insertNewArticle(article);
  }

  public Map<String, Object> listArticles(Map<String, Integer> pagingMap) {
    int pageNum = pagingMap.get("pageNum");
    pagingMap.put("pageNum", ((pageNum % 10) == 0 ? 10 : pageNum % 10));

    LOGGER.info("pagingMap: {}", pagingMap);

    List<ArticleVO> articlesList = boardDAO.selectPagedArticles(pagingMap);
    int articleTotalCount = boardDAO.selectCountTotalArticles();

    Map<String, Object> articlesMap = new HashMap<>();

    articlesMap.put("articlesList", articlesList);
    articlesMap.put("totArticles", articleTotalCount);

    return articlesMap;
  }
}
