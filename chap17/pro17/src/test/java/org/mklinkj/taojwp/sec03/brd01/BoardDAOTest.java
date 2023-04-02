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
}
