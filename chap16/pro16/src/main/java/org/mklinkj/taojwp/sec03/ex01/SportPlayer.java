package org.mklinkj.taojwp.sec03.ex01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SportPlayer {
  private String name;
  private int age;
  private String gender;
  private String nickname;
}
