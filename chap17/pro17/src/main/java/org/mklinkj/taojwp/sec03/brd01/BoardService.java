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
}
