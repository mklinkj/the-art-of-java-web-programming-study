package org.mklinkj.taojwp.sec03.brd01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.array;
import static org.mklinkj.taojwp.common.util.DBUtils.resetDB;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardDAOTest {

  private BoardDAO dao;

  @BeforeEach
  void beforeEach() {
    resetDB();
    dao = new BoardDAO();
  }

  @Test
  void selectAllArticles() {
    List<ArticleVO> list = dao.selectAllArticles();
    assertThat(list).isNotEmpty();
  }

  @Test
  void insertNewArticle() {
    ArticleVO article = new ArticleVO();
    article.setLevel(0);
    // articleNo는 설정할 필요없음
    article.setParentNo(0); // 루트 게시글
    article.setTitle("신규 게시글 제목");
    article.setContent("신규 게시글 내용");
    article.setImageFileName("스마일.png");
    article.setId("mklinkj");
    int newArticleNo = dao.insertNewArticle(article);

    assertThat(newArticleNo).isGreaterThan(0);
  }
}
