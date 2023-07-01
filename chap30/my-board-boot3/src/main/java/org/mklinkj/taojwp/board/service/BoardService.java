package org.mklinkj.taojwp.board.service;

import java.util.List;
import java.util.Map;
import org.mklinkj.taojwp.board.domain.ArticleVO;

public interface BoardService {

  List<ArticleVO> listArticles();

  int addArticle(ArticleVO article);

  ArticleVO viewArticle(int articleNo);

  int modArticle(ArticleVO article);

  List<Integer> removeArticle(int articleNo);

  int addReply(ArticleVO article);

  Map<String, Object> listArticles(Map<String, Integer> pagingMap);
}
