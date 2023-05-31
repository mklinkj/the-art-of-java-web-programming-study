package org.mklinkj.taojwp.aop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Calculator {
  public void add(int x, int y) {
    int result = x + y;
    LOGGER.info("결과: {}", result);
  }

  public void subtract(int x, int y) {
    int result = x - y;
    LOGGER.info("결과: {}", result);
  }

  public void multiply(int x, int y) {
    int result = x * y;
    LOGGER.info("결과: {}", result);
  }

  public void divide(int x, int y) {
    int result = x / y;
    LOGGER.info("결과: {}", result);
  }
}
