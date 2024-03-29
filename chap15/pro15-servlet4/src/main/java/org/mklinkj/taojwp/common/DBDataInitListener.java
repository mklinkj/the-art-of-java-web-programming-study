package org.mklinkj.taojwp.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("서버 시작시 DB 테이블 삭제 / 생성 / 기본 데이터를 넣어줌")
public class DBDataInitListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    DBUtils.resetDB();
  }
}
