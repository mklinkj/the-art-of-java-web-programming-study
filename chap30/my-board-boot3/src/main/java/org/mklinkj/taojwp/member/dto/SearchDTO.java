package org.mklinkj.taojwp.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("searchDTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchDTO {
  private String keyword;
  private SearchType type;
}
