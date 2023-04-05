package org.mklinkj.taojwp.sec03.brd01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.util.DBUtils.resetDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardServiceTest {

  private BoardService service;

  @BeforeEach
  void beforeEach() {
    resetDB();
    service = new BoardService();
  }

  @Test
  void removeArticle() {
    List<Integer> articleNumbersToDelete = service.removeArticle(2);
    // 자기 자신의 게시물 번호도 포함된다.
    assertThat(articleNumbersToDelete).containsExactlyInAnyOrder(2, 3, 5, 6);
  }

  @Test
  void listArticles() {
    Map<String, Integer> articlesMap = new HashMap<>();
    articlesMap.put("section", 2);
    articlesMap.put("pageNum", 11);

    Map<String, Object> result = service.listArticles(articlesMap);
    assertThat((List) result.get("articlesList")).isNotEmpty();
    assertThat((Integer) result.get("totArticles")).isGreaterThan(0);
  }
}
