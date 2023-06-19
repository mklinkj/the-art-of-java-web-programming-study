package org.mklinkj.qna.react_spring.service;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mklinkj.qna.react_spring.config.ServiceConfig;
import org.mklinkj.qna.react_spring.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringJUnitConfig(classes = {ServiceConfig.class})
@TestMethodOrder(OrderAnnotation.class)
class ArticleServiceTests {

  @Autowired private ArticleService articleService;

  @Autowired private EntityManager entityManager;

  @Order(1)
  @Transactional
  @Test
  void testDeleteById() {
    articleService.deleteById(1);
  }

  @Order(2)
  @Test
  void testFindById() {
    ArticleDTO article = articleService.findById(1);

    assertThat(article) //
        .isNotNull()
        .hasFieldOrPropertyWithValue("articleNo", 1);
  }

  @Transactional
  @Test
  void testSaveThenFind() {

    TypedQuery<Integer> query =
        entityManager.createQuery(
            """
              SELECT max(a.articleNo) + 1
                FROM Article a
            """,
            Integer.class);
    int newId = query.getSingleResult();

    LOGGER.info("newId: {}", newId);

    ArticleDTO articleDTO =
        ArticleDTO.builder() //
            .articleNo(newId)
            .writer("이순신%d".formatted(newId))
            .title("안녕하세요%d".formatted(newId))
            .content("새 상품을 소개합니다. %d".formatted(newId))
            .build();

    articleService.save(articleDTO);

    ArticleDTO newArticle = articleService.findById(newId);

    assertThat(newArticle).hasFieldOrPropertyWithValue("articleNo", newId);
  }
}
