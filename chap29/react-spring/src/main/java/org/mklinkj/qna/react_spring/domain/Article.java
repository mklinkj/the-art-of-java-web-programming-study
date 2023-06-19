package org.mklinkj.qna.react_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
  // ✨ 엔티티의 ID 값은 레퍼타입을 써야한다.
  // ✨ 원시(primitive)타입을 쓸 경우 몇몇 동작(Id 컬럼이 0인 행 삭제)이 제대로 안되는 경우가 있음
  @Id private Integer articleNo;

  private String writer;

  private String title;

  private String content;
}
