package org.mklinkj.taojwp.common;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JDBCDriverCleaner implements ServletContextListener {
  protected void deregisterJdbcDrivers(ServletContext servletContext) {
    for (Driver driver : Collections.list(DriverManager.getDrivers())) {
      // Gretty 환경에서는...
      // driver.getClass().getClassLoader(): java.net.URLClassLoader
      // servletContext.getClassLoader(): TomcatEmbeddedWebappClassLoader 가 됨.
      LOGGER.info("driver.getClass().getClassLoader(): {}", driver.getClass().getClassLoader());
      LOGGER.info("servletContext.getClassLoader(): {}", servletContext.getClassLoader());

      // Tomcat에서 데이터소스를 생성하지 않고, 웹애플리케이션 코드내에서 생성했으면
      // 아래 조건이 맞아서 잘 되었을지는 모르겠는데... 이 프로젝트는 이렇게 유지해보자.
      // if (driver.getClass().getClassLoader() == servletContext.getClassLoader()) {
      try {
        DriverManager.deregisterDriver(driver);
      } catch (SQLException ex) {
        LOGGER.warn(ex.getMessage(), ex);
      }
      // }
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    LOGGER.info("contextDestroyed 실행...");
    deregisterJdbcDrivers(event.getServletContext());
  }
}
