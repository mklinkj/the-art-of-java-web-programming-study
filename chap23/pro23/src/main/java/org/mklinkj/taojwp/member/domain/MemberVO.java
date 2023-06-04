package org.mklinkj.taojwp.member.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
  @NotEmpty
  @Size(min = 3, max = 10)
  private String id;

  @NotEmpty
  @Size(min = 4, max = 32)
  private String pwd;

  @NotEmpty
  @Size(min = 3, max = 30)
  private String name;

  @NotEmpty @Email private String email;

  private LocalDate joinDate;
}
