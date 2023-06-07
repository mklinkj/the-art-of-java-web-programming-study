package org.mklinkj.taojwp.member.dto;

import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Alias("searchType")
public enum SearchType {
  ID("아이디"),
  NAME("이름"),
  PWD("암호");

  @Getter private final String description;

  SearchType(String description) {
    this.description = description;
  }

  public String getName() {
    return name();
  }
}
