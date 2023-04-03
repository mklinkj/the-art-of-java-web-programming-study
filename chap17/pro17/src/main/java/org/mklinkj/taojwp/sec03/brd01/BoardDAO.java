package org.mklinkj.taojwp.sec03.brd01;

import static org.mklinkj.taojwp.common.util.DBUtils.executeMapper;

import java.util.List;
import java.util.function.Function;
import org.mklinkj.taojwp.mapper.BoardMapper;

public class BoardDAO {

  private <T> T execute(Function<BoardMapper, T> function, boolean autoCommit) {
    return executeMapper(function, BoardMapper.class, autoCommit);
  }

  public List<ArticleVO> selectAllArticles() {
    return execute(BoardMapper::selectAllArticles, false);
  }

  public int insertNewArticle(ArticleVO article) {
    return execute(
        boardMapper -> {
          int newArticleNo = boardMapper.selectMaxArticleNo() + 1;
          article.setArticleNo(newArticleNo);
          boardMapper.insertArticle(article);
          return newArticleNo;
        },
        true);
  }

  public ArticleVO selectArticle(int articleNo) {
    return execute(boardMapper -> boardMapper.selectOne(articleNo), false);
  }
}
