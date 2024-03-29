package org.mklinkj.taojwp.common.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mklinkj.taojwp.common.util.DBUtils;

@WebListener("서버 시작시 DB 테이블 삭제 / 생성 / 기본 데이터를 넣어줌")
public class DBDataInitListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    DBUtils.resetDB();
  }
}
