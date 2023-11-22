package org.mklinkj.taojwp.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("searchDTO")
@NoArgsConstructor // ✨ 파라미터가 없는 생성자가 반드시 필요하다.
@AllArgsConstructor
@Data
@Builder
public class SearchDTO {
  private String keyword;
  private SearchType type;
}
