package org.mklinkj.taojwp.member.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("searchDTO")
@Data
@Builder
public class SearchDTO {
  private String keyword;
  private SearchType type;
}
