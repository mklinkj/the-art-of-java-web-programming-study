package org.mklinkj.qna.react_spring.repository;

import org.mklinkj.qna.react_spring.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {}
