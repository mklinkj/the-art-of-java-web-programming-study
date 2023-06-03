package org.mklinkj.taojwp.member.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
  private String id;
  private String pwd;
  private String name;
  private String email;
  private LocalDate joinDate;
}
