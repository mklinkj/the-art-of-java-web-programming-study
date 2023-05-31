package org.mklinkj.taojwp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class LoggingAdvice implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    LOGGER.info("[메서드 호출 전]: LoggingAdvice");
    LOGGER.info("{} 메서드 호출 전", invocation.getMethod());

    Object object = invocation.proceed();

    LOGGER.info("[메서드 호출 후]: LoggingAdvice");
    LOGGER.info("{} 메서드 호출 후", invocation.getMethod());

    return object;
  }
}
