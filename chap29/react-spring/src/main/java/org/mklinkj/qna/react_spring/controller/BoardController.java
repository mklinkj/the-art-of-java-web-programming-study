package org.mklinkj.qna.react_spring.controller;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.mklinkj.qna.react_spring.dto.ArticleDTO;
import org.mklinkj.qna.react_spring.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
@Controller
public class BoardController {

  private final ArticleService articleService;

  @GetMapping("/all")
  public ResponseEntity<List<ArticleDTO>> list() {
    List<ArticleDTO> list = articleService.list();
    return ResponseEntity //
        .ok(list);
  }

  @GetMapping("/{articleNo}")
  public ResponseEntity<ArticleDTO> findArticle(@PathVariable("articleNo") Integer articleNo) {
    ArticleDTO article = articleService.findById(articleNo);
    return ResponseEntity //
        .ok(article);
  }

  // 경로임을 명확하게 표현하기 위해 /를 추가해주는 것이 낫겠다.
  @PostMapping("/")
  public ResponseEntity<Map<String, String>> addArticle(@RequestBody ArticleDTO articleDTO) {
    try {
      articleService.save(articleDTO);
      return ResponseEntity //
          .ok(Map.of("result", "success"));

    } catch (Exception e) {
      return ResponseEntity //
          .badRequest()
          .body(Map.of("result", "failure", "detail", e.getMessage()));
    }
  }

  @PutMapping("/{articleNo}")
  public ResponseEntity<Map<String, String>> modifyArticle(
      @PathVariable("articleNo") Integer articleNo, @RequestBody ArticleDTO articleDTO) {
    try {
      articleDTO.setArticleNo(articleNo);
      articleService.modify(articleDTO);
      return ResponseEntity //
          .ok(Map.of("result", "success"));
    } catch (Exception e) {
      return ResponseEntity //
          .badRequest()
          .body(Map.of("result", "failure", "detail", e.getMessage()));
    }
  }

  @DeleteMapping("/{articleNo}")
  public ResponseEntity<Map<String, String>> deleteArticle(
      @PathVariable("articleNo") Integer articleNo) {
    try {
      articleService.deleteById(articleNo);
      return ResponseEntity //
          .ok(Map.of("result", "success"));
    } catch (Exception e) {
      return ResponseEntity //
          .badRequest()
          .body(Map.of("result", "failure", "detail", e.getMessage()));
    }
  }
}
