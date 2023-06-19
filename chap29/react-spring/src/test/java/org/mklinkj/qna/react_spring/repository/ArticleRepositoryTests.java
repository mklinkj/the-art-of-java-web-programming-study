package org.mklinkj.qna.react_spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mklinkj.qna.react_spring.config.ServiceConfig;
import org.mklinkj.qna.react_spring.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Commit // Hibernate가 생성해주는 실행 쿼리 내용 잘 보려고, 우선 Commit 어노테이션을 붙였다.
@SpringJUnitConfig(classes = {ServiceConfig.class})
class ArticleRepositoryTests {

  @Autowired private ArticleRepository repository;

  @Test
  void testFindThenDelete() {
    Optional<Article> result = repository.findById(0);
    assertThat(result.isPresent()).isTrue();

    repository.delete(result.get());

    result = repository.findById(0);
    assertThat(result.isPresent()).isFalse();
  }
}
