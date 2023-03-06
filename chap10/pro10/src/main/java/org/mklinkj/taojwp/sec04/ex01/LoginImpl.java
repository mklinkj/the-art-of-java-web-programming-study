package org.mklinkj.taojwp.sec04.ex01;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginImpl implements HttpSessionBindingListener {

  private String userId;
  private String userPassword;

  static int totalUserCount = 0;

  @Override
  public void valueBound(HttpSessionBindingEvent event) {
    LOGGER.info("사용자 접속");
    ++totalUserCount;
  }

  @Override
  public void valueUnbound(HttpSessionBindingEvent event) {
    LOGGER.info("사용자 접속 해제");
    --totalUserCount;
  }
}
