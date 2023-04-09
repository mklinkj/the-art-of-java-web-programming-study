package org.mklinkj.taojwp.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.mklinkj.taojwp.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:application-context.xml")
class BoardServiceTest {

  @Autowired private BoardService service;

  @Autowired private DBDataInitializer dbDataInitializer;

  @BeforeEach
  void beforeEach() {
    dbDataInitializer.resetDB();
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
    articlesMap.put("pageNum", 1);

    Map<String, Object> result = service.listArticles(articlesMap);
    assertThat((List) result.get("articlesList")).isNotEmpty();
    assertThat((Integer) result.get("totArticles")).isGreaterThan(0);
  }
}
