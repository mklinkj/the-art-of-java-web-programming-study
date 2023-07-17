package org.mklinkj.taojwp.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.board.dao.BoardDAO;
import org.mklinkj.taojwp.board.domain.ArticleVO;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
  private final BoardDAO boardDAO;

  @Override
  public List<ArticleVO> listArticles() {
    return boardDAO.selectAllArticles();
  }

  @Override
  public int addArticle(ArticleVO article) {
    return boardDAO.insertNewArticle(article);
  }

  @Override
  public ArticleVO viewArticle(int articleNo) {
    return boardDAO.selectArticle(articleNo);
  }

  @Override
  public int modArticle(ArticleVO article) {
    return boardDAO.updateArticle(article);
  }

  @Override
  public List<Integer> removeArticle(int articleNo) {
    List<Integer> removedArticleNo = boardDAO.selectArticleNumbersToDelete(articleNo);
    // 게시물 자신만 지우지 않고, 답글까지 전부 지운다.
    boardDAO.deleteArticle(articleNo);
    return removedArticleNo;
  }

  @Override
  public int addReply(ArticleVO article) {
    return boardDAO.insertNewArticle(article);
  }

  @Override
  public Map<String, Object> listArticles(Map<String, Integer> pagingMap) {
    List<ArticleVO> articlesList = boardDAO.selectPagedArticles(pagingMap);
    int articleTotalCount = boardDAO.selectCountTotalArticles();

    Map<String, Object> articlesMap = new HashMap<>();

    // articlesMap 처음부터 도메인이 되었어야할 것 같은데...
    articlesMap.put("articlesList", articlesList);
    articlesMap.put("totArticles", articleTotalCount);

    return articlesMap;
  }
}
