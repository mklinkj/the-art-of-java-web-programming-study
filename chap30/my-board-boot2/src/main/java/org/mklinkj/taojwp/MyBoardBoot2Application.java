package org.mklinkj.taojwp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

@SpringBootApplication
public class MyBoardBoot2Application {
  public static void main(String[] args) {
    SpringApplication.run(MyBoardBoot2Application.class, args);
  }
}
