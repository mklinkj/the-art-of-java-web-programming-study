package org.mklinkj.taojwp.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.mklinkj.taojwp.board.ArticleVO;
import org.mklinkj.taojwp.board.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Slf4j
@SpringJUnitConfig(locations = "classpath:application-context.xml")
class BoardDAOTest {
  @Autowired private DBDataInitializer dbDataInitializer;

  @Autowired private BoardDAO dao;

  @BeforeEach
  void beforeEach() {
    dbDataInitializer.resetDB();
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

  @Test
  void deleteArticle() {

    int deletedRow = dao.deleteArticle(2);
    // 초기 데이터 글번호 2 기준으로 달린 답글이 4개이다.
    assertThat(deletedRow).isEqualTo(4);
  }

  @Test
  void selectArticleNumbersToDelete() {
    List<Integer> articleNumbersToDelete = dao.selectArticleNumbersToDelete(2);

    // 자기 자신의 게시물 번호도 포함된다.
    assertThat(articleNumbersToDelete).containsExactlyInAnyOrder(2, 3, 5, 6);
  }

  @Test
  void selectPagedArticles() {
    List<ArticleVO> pagedArticles = dao.selectPagedArticles(Map.of("section", 1, "pageNum", 1));
    assertThat(pagedArticles).isNotEmpty();
  }

  @Test
  void selectCountTotalArticles() {
    int count = dao.selectCountTotalArticles();
    LOGGER.info("total count: {}", count);
    assertThat(count).isGreaterThan(0);
  }
}
