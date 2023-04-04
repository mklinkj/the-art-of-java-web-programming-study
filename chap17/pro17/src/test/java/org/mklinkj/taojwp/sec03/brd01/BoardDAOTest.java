package org.mklinkj.taojwp.sec03.brd01;

import static org.assertj.core.api.Assertions.assertThat;
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

  @Test
  void selectArticle() {
    ArticleVO article = dao.selectArticle(1);
    assertThat(article).hasFieldOrPropertyWithValue("articleNo", 1);
  }

  @Test
  void selectArticle_NoExist() {
    ArticleVO article = dao.selectArticle(0);
    assertThat(article).isNull();
  }

  @Test
  void updateArticle() {
    resetDB();
    ArticleVO article =
        ArticleVO.builder() //
            .articleNo(1)
            .title("제목_수정")
            .content("내용_수정")
            .imageFileName("1.png")
            .build();
    int updatedCount = dao.updateArticle(article);
    assertThat(updatedCount).isEqualTo(1);
  }

  @Test
  void updateArticle_empty_imageFileName() {
    resetDB();
    ArticleVO article =
        ArticleVO.builder() //
            .articleNo(1)
            .title("제목_수정")
            .content("내용_수정")
            .imageFileName("")
            .build();
    int updatedCount = dao.updateArticle(article);
    assertThat(updatedCount).isEqualTo(1);
  }
}
