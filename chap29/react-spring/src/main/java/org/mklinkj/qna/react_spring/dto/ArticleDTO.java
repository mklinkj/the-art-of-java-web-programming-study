package org.mklinkj.qna.react_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
  private int articleNo;

  private String writer;

  private String title;

  private String content;
}
