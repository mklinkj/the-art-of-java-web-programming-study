package org.mklinkj.taojwp.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchDTO {
  private String keyword;
  private SearchType type;
}
