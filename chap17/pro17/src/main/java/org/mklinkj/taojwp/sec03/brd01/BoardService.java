package org.mklinkj.taojwp.sec03.brd01;

import java.util.List;

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
}
