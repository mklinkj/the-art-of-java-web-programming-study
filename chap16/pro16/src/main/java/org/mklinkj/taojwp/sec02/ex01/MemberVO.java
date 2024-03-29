package org.mklinkj.taojwp.sec02.ex01;

import java.time.LocalDateTime;
import lombok.Builder;
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
