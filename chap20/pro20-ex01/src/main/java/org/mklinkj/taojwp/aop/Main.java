package org.mklinkj.taojwp.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    try (ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("application-context.xml")) {

      Calculator cal = context.getBean("proxyCal", Calculator.class);

      cal.add(100, 20);
      System.out.println();

      cal.subtract(100, 20);
      System.out.println();

      cal.multiply(100, 20);
      System.out.println();

      cal.divide(100, 20);
      System.out.println();
    }
  }
}
