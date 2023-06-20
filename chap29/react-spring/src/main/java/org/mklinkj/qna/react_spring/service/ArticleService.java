package org.mklinkj.qna.react_spring.service;

import jakarta.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mklinkj.qna.react_spring.domain.Article;
import org.mklinkj.qna.react_spring.dto.ArticleDTO;
import org.mklinkj.qna.react_spring.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {
  private final ModelMapper modelMapper;

  private final ArticleRepository articleRepository;

  public List<ArticleDTO> list() {
    return articleRepository.findAll().stream()
        .map(a -> modelMapper.map(a, ArticleDTO.class))
        .toList();
  }

  public ArticleDTO findById(Integer articleNo) {
    Optional<Article> result = articleRepository.findById(articleNo);
    Article article = result.orElseThrow();
    return modelMapper.map(article, ArticleDTO.class);
  }

  @Transactional
  public void register(ArticleDTO articleDTO) {
    Optional<Article> result = articleRepository.findById(articleDTO.getArticleNo());

    if (result.isPresent()) {
      throw new EntityExistsException("해당 게시물 ID는 이미 있어요 😅");
    }
    Article article = modelMapper.map(articleDTO, Article.class);
    articleRepository.save(article);
  }

  public void deleteById(Integer articleNo) {
    articleRepository.deleteById(articleNo);
  }

  @Transactional
  public void modify(ArticleDTO articleDTO) {

    Optional<Article> result = articleRepository.findById(articleDTO.getArticleNo());

    Article currentArticle = result.orElseThrow();

    currentArticle.changeTitle(articleDTO.getTitle());
    currentArticle.changeContent(articleDTO.getContent());
    currentArticle.changeWriter(articleDTO.getWriter());

    articleRepository.save(currentArticle);
  }
}
