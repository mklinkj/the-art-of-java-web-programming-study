package org.mklinkj.taojwp.sec01.ex01;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class MemberVO {
  private String id;
  private String pwd;
  private String name;
  private String email;
  private LocalDateTime joinDate;

  public MemberVO() {
    LOGGER.info("MemberVO 생성자 호출");
  }

  public MemberVO(String id, String pwd, String name, String email) {
    this.id = id;
    this.pwd = pwd;
    this.name = name;
    this.email = email;
  }
}
