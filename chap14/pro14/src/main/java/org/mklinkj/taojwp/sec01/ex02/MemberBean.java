package org.mklinkj.taojwp.sec01.ex02;

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
  private Address address;
}
