package org.mklinkj.taojwp.sec02.ex01;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberBean {

  private String id;
  private String pwd;
  private String name;
  private String email;
  private LocalDateTime joinDate;

  public MemberBean(String id, String pwd, String name, String email) {
    this.id = id;
    this.pwd = pwd;
    this.name = name;
    this.email = email;
  }
}
