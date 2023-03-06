package org.mklinkj.taojwp.sec04.ex02;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginImpl implements HttpSessionListener {

  private String userId;
  private String userPassword;

  static int totalUserCount = 0;

  @Override
  public void sessionCreated(HttpSessionEvent sessionEvent) {
    LOGGER.info("세션 생성 접속");
    ++totalUserCount;
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    LOGGER.info("사용자 접속 해제");
    --totalUserCount;
  }
}
