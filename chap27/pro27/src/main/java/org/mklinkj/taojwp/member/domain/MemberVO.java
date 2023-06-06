package org.mklinkj.taojwp.member.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("memberVO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
