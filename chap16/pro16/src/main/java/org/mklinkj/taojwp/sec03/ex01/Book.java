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
public class Book {
  private String title;
  private String writer;
  private int price;
  private String genre;
  private String image;
}
