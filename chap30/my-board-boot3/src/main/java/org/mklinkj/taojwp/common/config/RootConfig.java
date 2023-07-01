package org.mklinkj.taojwp.common.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
@Configuration
@PropertySource("classpath:config/db-oracle.properties")
public class RootConfig {

  private final Environment environment;

  /*
   * 스프링 부트가 자동 설정해주는 것을 그대로 유지하면서도
   * 프로퍼티 처리는 별도로 관리하고 싶어서 아래와 같이 처리
   */
  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder.create() //
        .driverClassName(environment.getProperty("jdbc.driver"))
        .url(environment.getProperty("jdbc.url"))
        .username(environment.getProperty("jdbc.username"))
        .password(environment.getProperty("jdbc.password"))
        .build();
  }

  @Primary
  @Bean(initMethod = "resetDB")
  DBDataInitializer dbDataInitializer() {
    return new DBDataInitializer(dataSource(), "sql/oracle/init-sql.sql");
  }
}
