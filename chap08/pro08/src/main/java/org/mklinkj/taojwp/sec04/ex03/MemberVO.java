package org.mklinkj.taojwp.sec04.ex03;

import java.time.LocalDateTime;
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
  private LocalDateTime joinDate;
}
